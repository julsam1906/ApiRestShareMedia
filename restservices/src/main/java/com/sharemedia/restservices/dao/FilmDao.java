package com.sharemedia.restservices.dao;

import com.sharemedia.restservices.model.Film;

import java.util.List;
import java.util.Map;

public interface FilmDao {

    void saveData(Film film);

    void removeData(String key);

    List<Film> getAll();

    void updateData(Film film);

}
