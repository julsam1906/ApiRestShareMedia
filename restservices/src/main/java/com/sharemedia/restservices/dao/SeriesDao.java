package com.sharemedia.restservices.dao;

import java.util.List;
import java.util.Map;

import com.sharemedia.restservices.model.Series;

public interface SeriesDao {

    void saveSerie(Series serie);

    void removeSerie(String key);

    void updateSerie(Series serie);

    List<Series> getAll();


}
