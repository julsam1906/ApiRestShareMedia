package com.sharemedia.restservices.dao.mapper;

import com.sharemedia.restservices.datakey.BiereDataKey;
import com.sharemedia.restservices.model.Bieres;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BiereRowMapper implements RowMapper<Bieres> {

    @Override
    public Bieres mapRow(ResultSet resultSet, int i) throws SQLException {
        Bieres bieres = new Bieres();
        bieres.setKey(resultSet.getString(BiereDataKey.KEY));
        bieres.setBrasserie(resultSet.getString(BiereDataKey.BRASSERIE));
        bieres.setShop(resultSet.getString(BiereDataKey.SHOP));
        bieres.setHoublons(resultSet.getString(BiereDataKey.HOUBLONS));
        bieres.setDescriptif(resultSet.getString(BiereDataKey.DESCRIPTIF));
        bieres.setImage(resultSet.getString(BiereDataKey.IMAGE));
        bieres.setTitre(resultSet.getString(BiereDataKey.TITRE));
        return bieres;
    }
}
