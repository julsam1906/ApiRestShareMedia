package com.sharemedia.restservices;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
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
import com.google.gson.Gson;
import com.sharemedia.restservices.dao.SeriesDao;
import com.sharemedia.restservices.model.Series;

@Controller
@CrossOrigin(origins = {"http://localhost:3000", "https://youtube.com"}, allowCredentials = "true")
public class SeriesController {

	@Autowired
	private SeriesDao serieDao;
	private Log log = LogFactory.getLog(SeriesController.class);

	@PostMapping(value = "/sharemedia/saveSerie")
	@ResponseBody
	public void saveSerie(@RequestBody String serie) {
		log.info("Serie: " + serie);
		Gson g = new Gson();
		Series p = g.fromJson(serie, Series.class);
		serieDao.saveSerie(p);
	}

	@PostMapping(value = "/sharemedia/updateSerie")
	@ResponseBody
	public void updateZero(@RequestBody String serie) {
		Gson g = new Gson();
		Series p = g.fromJson(serie, Series.class);
		serieDao.updateSerie(p);
	}

	@GetMapping(value = "/sharemedia/allSeries", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllSeries() {
		Map<String, Series> map = new HashMap<String, Series>();
		String json = "";
		Map<String, Series> series = serieDao.getAll();
		final Instant deadline = Instant.now().plus(500, ChronoUnit.MILLIS);
		while ((Instant.now().isBefore(deadline)) && (series.size() == 0)) {
		}

		for (Map.Entry<String, Series> entry : series.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		ObjectMapper mapper = new ObjectMapper();

		try {
			json = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@DeleteMapping("/sharemedia/deleteSerie")
	@ResponseBody
	public void deleteFilm(@RequestParam(value = "titre") String titre) {
		serieDao.removeSerie(titre);
	}

}
