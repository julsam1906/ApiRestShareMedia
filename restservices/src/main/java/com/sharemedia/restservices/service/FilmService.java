package com.sharemedia.restservices.service;

import com.sharemedia.restservices.model.Film;

import java.util.List;

public interface FilmService {

    void ajouterFilm(Film film);

    void miseAjourFilm(Film film);

    void supprimerFilm(String key);

    List<Film> getAllFilm();
}
