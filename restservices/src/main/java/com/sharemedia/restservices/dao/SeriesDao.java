package com.sharemedia.restservices.dao;

import java.util.Map;

import com.sharemedia.restservices.model.Series;

public interface SeriesDao {
	
	/**
	 * 
	 * @param serie
	 */
	public void saveSerie(Series serie);
	
	/**
	 * 
	 * @param serie
	 */
	public void removeSerie(String titre);
	
	/**
	 * 
	 * @param serie
	 */
	public void updateSerie(Series serie);
	
	/**
	 * 
	 * @return
	 */
	public Map<String, Series> getAll();
	



}
