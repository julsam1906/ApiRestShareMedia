package com.sharemedia.restservices.model;

public class Series {
	
	private String titre;
	private String plateforme;
	private String lien;
	private String descriptif;
	private String key;
	
	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}
	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	/**
	 * @return the plateforme
	 */
	public String getPlateforme() {
		return plateforme;
	}
	/**
	 * @param plateforme the plateforme to set
	 */
	public void setPlateforme(String plateforme) {
		this.plateforme = plateforme;
	}
	/**
	 * @return the lien
	 */
	public String getLien() {
		return lien;
	}
	/**
	 * @param lien the lien to set
	 */
	public void setLien(String lien) {
		this.lien = lien;
	}
	/**
	 * @return the descriptif
	 */
	public String getDescriptif() {
		return descriptif;
	}
	/**
	 * @param descriptif the descriptif to set
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @param titre
	 * @param plateforme
	 * @param lien
	 * @param descriptif
	 */
	public Series(String titre, String plateforme, String lien, String descriptif) {
		super();
		this.titre = titre;
		this.plateforme = plateforme;
		this.lien = lien;
		this.descriptif = descriptif;
	}
	/**
	 * 
	 */
	public Series() {
		super();
	}
	
	

}
