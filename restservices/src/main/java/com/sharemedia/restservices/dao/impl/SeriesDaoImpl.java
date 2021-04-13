package com.sharemedia.restservices.dao.impl;

import com.sharemedia.restservices.dao.SeriesDao;
import com.sharemedia.restservices.dao.mapper.SeriesRowMapper;
import com.sharemedia.restservices.datakey.SeriesDataKey;
import com.sharemedia.restservices.model.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeriesDaoImpl implements SeriesDao {

    private static final String SAVE = "INSERT INTO series (key, titre, plateforme, lien, descriptif)" +
            " VALUES (:key, :titre, :plateforme, :lien, :descriptif)";

    private static final String LIRE = "SELECT * FROM series";

    private static final String SUPPRIMER = "DELETE FROM series where key= :key";

    private static final String UPDATE = "UPDATE series set titre= :titre," +
            " plateforme= :plateforme," +
            " lien= :lien," +
            " descriptif= :descriptif where key=:key";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveSerie(Series serie) {
        jdbcTemplate.update(SAVE, parameterSource(serie));
    }

    @Override
    public void removeSerie(String key) {
        jdbcTemplate.update(SUPPRIMER, new MapSqlParameterSource(SeriesDataKey.KEY, key));
    }

    @Override
    public void updateSerie(Series serie) {
        jdbcTemplate.update(UPDATE, parameterSource(serie));
    }

    @Override
    public List<Series> getAll() {
        return jdbcTemplate.query(LIRE, new SeriesRowMapper());
    }

    private MapSqlParameterSource parameterSource(Series serie){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(SeriesDataKey.KEY, serie.getKey());
        parameterSource.addValue(SeriesDataKey.DESCRIPTIF, serie.getDescriptif());
        parameterSource.addValue(SeriesDataKey.LIEN, serie.getLien());
        parameterSource.addValue(SeriesDataKey.PLATEFORME, serie.getPlateforme());
        parameterSource.addValue(SeriesDataKey.TITRE, serie.getTitre());
        return parameterSource;
    }
}
