package com.sharemedia.restservices;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharemedia.restservices.dao.impl.FilmDaoImpl;
import com.sharemedia.restservices.model.Film;

@Controller
@CrossOrigin(origins = "http://localhost:3000", allowCredentials ="true")
public class RestController {
	

	@Autowired
	private FilmDaoImpl filmDao;
	private Log log = LogFactory.getLog(RestController.class);

	@PostMapping(value="/sharemedia/saveFilm", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void saveFilm(@RequestBody Film film) {
		log.info("Film: "+film);
		filmDao.saveData(film);
	}

	@GetMapping(value="/sharemedia/allFilms", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllFilms() {
		Map<Integer, Film> map = new HashMap<Integer, Film>();
		String json = "";
		List<Film> films = filmDao.getAll();
		final Instant deadline = Instant.now().plus(500, ChronoUnit.MILLIS);
		while(Instant.now().isBefore(deadline)) {
			log.info(films.size());
		}
		int i = 1;
		for (Film film : films) {
			log.info(film);
			map.put(i, film);
			i++;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@DeleteMapping("/sharemedia/deleteFilm")
	@ResponseBody
	public void deleteFilm(@RequestParam(value = "title") String title) {
		filmDao.removeData(title);
	}



}
