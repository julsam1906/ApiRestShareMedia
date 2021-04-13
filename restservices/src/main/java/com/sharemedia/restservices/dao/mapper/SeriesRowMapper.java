package com.sharemedia.restservices.dao.mapper;

import com.sharemedia.restservices.datakey.SeriesDataKey;
import com.sharemedia.restservices.model.Series;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeriesRowMapper implements RowMapper<Series> {
    @Override
    public Series mapRow(ResultSet resultSet, int i) throws SQLException {
        Series series = new Series();
        series.setDescriptif(resultSet.getString(SeriesDataKey.DESCRIPTIF));
        series.setKey(resultSet.getString(SeriesDataKey.KEY));
        series.setLien(resultSet.getString(SeriesDataKey.LIEN));
        series.setPlateforme(resultSet.getString(SeriesDataKey.PLATEFORME));
        series.setTitre(resultSet.getString(SeriesDataKey.TITRE));
        return series;
    }
}
