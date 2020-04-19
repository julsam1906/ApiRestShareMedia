package com.sharemedia.restservices.dao;

import java.util.Map;

import com.sharemedia.restservices.model.ZeroDechet;

public interface ZeroDechetDao {
	
	/**
	 * 
	 * @param zero
	 */
	public void saveZero(ZeroDechet zero);
	
	/**
	 * 
	 * @param zero
	 */
	public void removeZero(String titre);
	
	/**
	 * 
	 * @param zero
	 */
	public void updateZero(ZeroDechet zero);
	
	/**
	 * 
	 * @return
	 */
	public Map<String, ZeroDechet> getAll();
	

}
