package com.sharemedia.restservices.dao;

import java.util.List;

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
	public List<Film> getAll();

}
