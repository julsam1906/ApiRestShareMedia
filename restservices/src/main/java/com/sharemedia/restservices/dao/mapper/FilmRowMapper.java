package com.sharemedia.restservices.dao.mapper;

import com.sharemedia.restservices.datakey.FilmDataKey;
import com.sharemedia.restservices.model.Film;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmRowMapper implements RowMapper<Film> {

    @Override
    public Film mapRow(ResultSet resultSet, int i) throws SQLException {
        Film film = new Film();
        film.setKey(resultSet.getString(FilmDataKey.KEY));
        film.setDescriptif(resultSet.getString(FilmDataKey.DESCRIPTIF));
        film.setPlateform(resultSet.getString(FilmDataKey.PLATEFORM));
        film.setTitle(resultSet.getString(FilmDataKey.TITRE));
        film.setUrl(resultSet.getString(FilmDataKey.URL));
        return film;
    }
}
