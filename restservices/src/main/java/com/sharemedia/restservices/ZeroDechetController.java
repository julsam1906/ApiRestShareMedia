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
import com.sharemedia.restservices.dao.impl.ZeroDechetDaoImpl;
import com.sharemedia.restservices.model.ZeroDechet;

@Controller
@CrossOrigin(origins = {"http://localhost:3000", "https://youtube.com"}, allowCredentials = "true")
public class ZeroDechetController {

	@Autowired
	private ZeroDechetDaoImpl zeroDao;
	private Log log = LogFactory.getLog(ZeroDechetController.class);

	@PostMapping(value = "/sharemedia/saveZero")
	@ResponseBody
	public void saveZero(@RequestBody String zero) {
		log.info("Zero: " + zero);
		Gson g = new Gson();
		ZeroDechet p = g.fromJson(zero, ZeroDechet.class);
		zeroDao.saveZero(p);
		;
	}

	@PostMapping(value = "/sharemedia/updateZero")
	@ResponseBody
	public void updateZero(@RequestBody String zero) {
		Gson g = new Gson();
		ZeroDechet p = g.fromJson(zero, ZeroDechet.class);
		zeroDao.updateZero(p);
	}

	@GetMapping(value = "/sharemedia/allZeros", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllZeros() {
		Map<String, ZeroDechet> map = new HashMap<String, ZeroDechet>();
		String json = "";
		Map<String, ZeroDechet> zeros = zeroDao.getAll();
		final Instant deadline = Instant.now().plus(500, ChronoUnit.MILLIS);
		while ((Instant.now().isBefore(deadline)) && (zeros.size() == 0)) {
		}

		for (Map.Entry<String, ZeroDechet> entry : zeros.entrySet()) {
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

	@DeleteMapping("/sharemedia/deleteZero")
	@ResponseBody
	public void deleteFilm(@RequestParam(value = "titre") String titre) {
		zeroDao.removeZero(titre);
	}

}
