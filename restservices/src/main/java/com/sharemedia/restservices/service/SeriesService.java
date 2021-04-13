package com.sharemedia.restservices.service;

import com.sharemedia.restservices.model.Series;

import java.util.List;

public interface SeriesService {

    void ajouterSerie(Series serie);

    void miseAjourSerie(Series serie);

    void supprimerSerie(String key);

    List<Series> getAllSerie();
}
