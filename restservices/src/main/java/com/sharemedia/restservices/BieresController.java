package com.sharemedia.restservices;

import com.sharemedia.restservices.model.Bieres;
import com.sharemedia.restservices.service.BieresService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sharemedia")
@CrossOrigin(origins = {"http://localhost:3000", "https://youtube.com"}, allowCredentials = "true")
public class BieresController {

	@Autowired
	private BieresService bieresService;

	private Log log = LogFactory.getLog(BieresController.class);

	@PostMapping(value = "/biere/save")
	@ResponseBody
	public ResponseEntity<String> saveBiere(@RequestBody Bieres bieres) {
		log.info("Biere: " + bieres);
		bieresService.ajouterBieres(bieres);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping(value = "/biere/update")
	@ResponseBody
	public ResponseEntity<String> updateBiere(@RequestBody Bieres beer) {
		bieresService.miseAjourBiere(beer);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value = "/biere/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Bieres>> getAllBieres() {
		List<Bieres> bieres =  bieresService.getAllBieres();
		return new ResponseEntity<>(bieres, HttpStatus.OK);
	}

	@DeleteMapping(value = "/biere/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> deleteBiere(@RequestParam String key) {
		bieresService.supprimerBiere(key);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
