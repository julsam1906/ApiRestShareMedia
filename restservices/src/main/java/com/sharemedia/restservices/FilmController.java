package com.sharemedia.restservices;

import com.sharemedia.restservices.model.Bieres;
import com.sharemedia.restservices.model.Film;
import com.sharemedia.restservices.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sharemedia")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PostMapping(value = "/film/save")
    @ResponseBody
    public ResponseEntity<String> saveFilm(@RequestBody Film film) {
        filmService.ajouterFilm(film);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/film/update")
    @ResponseBody
    public ResponseEntity<String> updateFilm(@RequestBody Film film) {
        filmService.miseAjourFilm(film);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/film/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> films =  filmService.getAllFilm();
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @DeleteMapping(value = "/film/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> deleteFilm(@RequestParam String key) {
        filmService.supprimerFilm(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
