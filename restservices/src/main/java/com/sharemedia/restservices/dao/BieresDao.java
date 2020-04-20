package com.sharemedia.restservices.dao;

import java.util.Map;

import com.sharemedia.restservices.model.Bieres;
import com.sharemedia.restservices.model.ZeroDechet;

public interface BieresDao {
	
	/**
	 * 
	 * @param beer
	 */
	public void saveBiere(Bieres beer);
	
	/**
	 * 
	 * @param titre
	 */
	public void removeBiere(String titre);
	
	/**
	 * 
	 * @param beer
	 */
	public void updateBiere(Bieres beer);
	
	/**
	 * 
	 * @return
	 */
	public Map<String, Bieres> getAll();
	



}
