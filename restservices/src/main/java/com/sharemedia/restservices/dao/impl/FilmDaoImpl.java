package com.sharemedia.restservices.dao.impl;

import com.sharemedia.restservices.dao.FilmDao;
import com.sharemedia.restservices.dao.mapper.FilmRowMapper;
import com.sharemedia.restservices.datakey.FilmDataKey;
import com.sharemedia.restservices.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmDaoImpl implements FilmDao {

    private static final String SAVE = "INSERT INTO film (key, title, plateform, url, descriptif)" +
            " VALUES (:key, :title, :plateform, :url, :descriptif)";

    private static final String LIRE = "SELECT * FROM film";

    private static final String SUPPRIMER = "DELETE FROM film where key= :key";

    private static final String UPDATE = "UPDATE film set title= :title," +
            " plateform= :plateform," +
            " url= :url," +
            " descriptif= :descriptif where key=:key";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveData(Film film) {
        jdbcTemplate.update(SAVE, parameterSource(film));
    }

    @Override
    public void removeData(String key) {
        jdbcTemplate.update(SUPPRIMER, new MapSqlParameterSource(FilmDataKey.KEY, key));
    }

    @Override
    public List<Film> getAll() {
        return jdbcTemplate.query(LIRE, new FilmRowMapper());
    }

    @Override
    public void updateData(Film film) {
        jdbcTemplate.update(UPDATE, parameterSource(film));
    }

    private MapSqlParameterSource parameterSource(Film film) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(FilmDataKey.KEY, film.getKey());
        parameterSource.addValue(FilmDataKey.DESCRIPTIF, film.getDescriptif());
        parameterSource.addValue(FilmDataKey.PLATEFORM, film.getPlateform());
        parameterSource.addValue(FilmDataKey.TITRE, film.getTitle());
        parameterSource.addValue(FilmDataKey.URL, film.getUrl());
        return parameterSource;

    }
}
