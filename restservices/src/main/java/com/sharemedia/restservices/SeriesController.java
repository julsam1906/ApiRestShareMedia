package com.sharemedia.restservices;

import com.sharemedia.restservices.model.Series;
import com.sharemedia.restservices.service.SeriesService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/sharemedia")
@CrossOrigin(origins = {"http://localhost:3000", "https://youtube.com"}, allowCredentials = "true")
public class SeriesController {

    private Log log = LogFactory.getLog(SeriesController.class);

    @Autowired
    private SeriesService seriesService;

    @PostMapping(value = "/serie/save")
    @ResponseBody
    public ResponseEntity<String> saveSerie(@RequestBody Series serie) {
        log.info("Debut series save : " +serie);
        seriesService.ajouterSerie(serie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/serie/update")
    @ResponseBody
    public ResponseEntity<String> updateSerie(@RequestBody Series series) {
        seriesService.miseAjourSerie(series);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/serie/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Series>> getAllSeries() {
        List<Series> series =  seriesService.getAllSerie();
        return new ResponseEntity<>(series, HttpStatus.OK);
    }

    @DeleteMapping(value = "/serie/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> deleteSerie(@RequestParam String key) {
        seriesService.supprimerSerie(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
