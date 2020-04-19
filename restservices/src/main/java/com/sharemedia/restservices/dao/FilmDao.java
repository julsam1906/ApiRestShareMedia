package com.sharemedia.restservices.dao;

import java.util.Map;

import com.sharemedia.restservices.model.Film;

public interface FilmDao {

	/**
	 * 
	 * @param film
	 */
	public void saveData(Film film);
	
	/**
	 * 
	 * @param title
	 */
	public void removeData(String title);
	
	/**
	 * 
	 * @return
	 */
	public Map<String, Film> getAll();
	
	/**
	 * 
	 * @param film
	 */
	public void updateData(Film film, String key);

}
