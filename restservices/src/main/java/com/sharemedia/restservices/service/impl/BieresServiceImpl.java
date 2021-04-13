package com.sharemedia.restservices.service.impl;

import com.sharemedia.restservices.dao.BieresDao;
import com.sharemedia.restservices.model.Bieres;
import com.sharemedia.restservices.service.BieresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BieresServiceImpl implements BieresService {

    @Autowired
    private BieresDao bieresDao;


    @Override
    public List<Bieres> getAllBieres() {
        return bieresDao.getAll();
    }

    @Override
    public void ajouterBieres(Bieres bieres) {
        bieresDao.saveBiere(bieres);
    }

    @Override
    public void miseAjourBiere(Bieres bieres) {
        bieresDao.updateBiere(bieres);
    }

    @Override
    public void supprimerBiere(String key) {
        bieresDao.removeBiere(key);
    }
}
