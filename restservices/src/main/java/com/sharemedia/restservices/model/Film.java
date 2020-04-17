package com.sharemedia.restservices.model;

/**
 * 
 * @author noverka
 *
 */
public class Film {
	
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private String plateform;
	private String url;
	private String descriptif;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlateform() {
		return plateform;
	}
	public void setPlateform(String plateform) {
		this.plateform = plateform;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public Film() {
		super();
	}
	public Film(String title, String plateform, String url, String descriptif) {
		super();
		this.title = title;
		this.plateform = plateform;
		this.url = url;
		this.descriptif = descriptif;
	}

}
