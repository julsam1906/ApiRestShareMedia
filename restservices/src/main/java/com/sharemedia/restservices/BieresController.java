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
import com.sharemedia.restservices.dao.impl.BieresDaoImpl;
import com.sharemedia.restservices.model.Bieres;

@Controller
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class BieresController {

	@Autowired
	private BieresDaoImpl beerDao;
	private Log log = LogFactory.getLog(BieresController.class);

	@PostMapping(value = "/sharemedia/saveBiere")
	@ResponseBody
	public void saveZero(@RequestBody String beer) {
		log.info("Biere: " + beer);
		Gson g = new Gson();
		Bieres p = g.fromJson(beer, Bieres.class);
		beerDao.saveBiere(p);
		;
	}

	@PostMapping(value = "/sharemedia/updateBiere")
	@ResponseBody
	public void updateBiere(@RequestBody String beer) {
		Gson g = new Gson();
		Bieres p = g.fromJson(beer, Bieres.class);
		beerDao.updateBiere(p);
	}

	@GetMapping(value = "/sharemedia/allBieres", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllZeros() {
		Map<String, Bieres> map = new HashMap<String, Bieres>();
		String json = "";
		Map<String, Bieres> beers = beerDao.getAll();
		final Instant deadline = Instant.now().plus(500, ChronoUnit.MILLIS);
		while ((Instant.now().isBefore(deadline)) && (beers.size() == 0)) {
		}

		for (Map.Entry<String, Bieres> entry : beers.entrySet()) {
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

	@DeleteMapping("/sharemedia/deleteBiere")
	@ResponseBody
	public void deleteFilm(@RequestParam(value = "titre") String titre) {
		beerDao.removeBiere(titre);
	}

}
