package com.sharemedia.restservices.dao.impl;

import com.sharemedia.restservices.datakey.BiereDataKey;
import com.sharemedia.restservices.dao.BieresDao;
import com.sharemedia.restservices.dao.mapper.BiereRowMapper;
import com.sharemedia.restservices.model.Bieres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BieresDaoImpl implements BieresDao {

    private static final String SAVE = "INSERT INTO beers (key, brasserie, shop, image, houblons, descriptif, titre)" +
            " VALUES (:key, :brasserie, :shop, :image, :houblons, :descriptif, :titre)";

    private static final String LIRE = "SELECT * FROM beers";

    private static final String SUPPRIMER = "DELETE FROM beers where key= :key";

    private static final String UPDATE = "UPDATE beers set brasserie= :brasserie," +
            " shop= :shop," +
            " image= :image," +
            " houblons= :houblons," +
            " descriptif= :descriptif, titre= :titre where key=:key";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveBiere(Bieres bieres) {
        jdbcTemplate.update(SAVE, parameterSource(bieres));
    }

    @Override
    public void removeBiere(String key) {
        jdbcTemplate.update(SUPPRIMER, new MapSqlParameterSource(BiereDataKey.KEY, key));
    }

    @Override
    public void updateBiere(Bieres bieres) {
        jdbcTemplate.update(UPDATE, parameterSource(bieres));
    }

    @Override
    public List<Bieres> getAll() {
        return jdbcTemplate.query(LIRE, new BiereRowMapper());
    }

    /**
     *
     * @param bieres
     * @return
     */
    private MapSqlParameterSource parameterSource(Bieres bieres){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(BiereDataKey.BRASSERIE, bieres.getBrasserie());
        parameterSource.addValue(BiereDataKey.SHOP, bieres.getShop());
        parameterSource.addValue(BiereDataKey.IMAGE, bieres.getImage());
        parameterSource.addValue(BiereDataKey.HOUBLONS, bieres.getHoublons());
        parameterSource.addValue(BiereDataKey.DESCRIPTIF, bieres.getDescriptif());
        parameterSource.addValue(BiereDataKey.TITRE, bieres.getTitre());
        parameterSource.addValue(BiereDataKey.KEY, bieres.getKey());
        return parameterSource;
    }
}
