package com.sharemedia.restservices.service;

import com.sharemedia.restservices.model.Bieres;

import java.util.List;

public interface BieresService {

    List<Bieres> getAllBieres();

    void ajouterBieres(Bieres bieres);

    void miseAjourBiere(Bieres bieres);

    void supprimerBiere(String key);
}
