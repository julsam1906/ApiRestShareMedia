package com.sharemedia.restservices.service.impl;

import com.sharemedia.restservices.dao.SeriesDao;
import com.sharemedia.restservices.model.Film;
import com.sharemedia.restservices.model.Series;
import com.sharemedia.restservices.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {

    @Autowired
    private SeriesDao dao;

    @Override
    public void ajouterSerie(Series serie) {
        dao.saveSerie(serie);
    }

    @Override
    public void miseAjourSerie(Series serie) {
        dao.updateSerie(serie);
    }

    @Override
    public void supprimerSerie(String key) {
        dao.removeSerie(key);
    }

    @Override
    public List<Series> getAllSerie() {
        return dao.getAll();
    }
}
