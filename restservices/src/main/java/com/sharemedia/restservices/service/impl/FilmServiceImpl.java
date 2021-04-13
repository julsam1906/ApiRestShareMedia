package com.sharemedia.restservices.service.impl;

import com.sharemedia.restservices.dao.BieresDao;
import com.sharemedia.restservices.dao.FilmDao;
import com.sharemedia.restservices.model.Film;
import com.sharemedia.restservices.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmDao filmDao;

    @Override
    public void ajouterFilm(Film film) {
        filmDao.saveData(film);
    }

    @Override
    public void miseAjourFilm(Film film) {
        filmDao.updateData(film);
    }

    @Override
    public void supprimerFilm(String key) {
        filmDao.removeData(key);
    }

    @Override
    public List<Film> getAllFilm() {
        return filmDao.getAll();
    }
}
