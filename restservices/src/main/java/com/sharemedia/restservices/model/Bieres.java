package com.sharemedia.restservices.model;

public class Bieres {

	private String brasserie;
	private String shop;
	private String image;
	private String houblons;
	private String descriptif;
	private String titre;
	private String key;

	/**
	 * @return the brasserie
	 */
	public String getBrasserie() {
		return brasserie;
	}

	/**
	 * @param brasserie the brasserie to set
	 */
	public void setBrasserie(String brasserie) {
		this.brasserie = brasserie;
	}

	/**
	 * @return the shop
	 */
	public String getShop() {
		return shop;
	}

	/**
	 * @param shop the shop to set
	 */
	public void setShop(String shop) {
		this.shop = shop;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the houblons
	 */
	public String getHoublons() {
		return houblons;
	}

	/**
	 * @param houblons the houblons to set
	 */
	public void setHoublons(String houblons) {
		this.houblons = houblons;
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
	 * @param brasserie
	 * @param shop
	 * @param image
	 * @param houblons
	 * @param descriptif
	 * @param titre
	 */
	public Bieres(String brasserie, String shop, String image, String houblons, String descriptif, String titre) {
		super();
		this.brasserie = brasserie;
		this.shop = shop;
		this.image = image;
		this.houblons = houblons;
		this.descriptif = descriptif;
		this.titre = titre;
	}

	/**
	 * 
	 */
	public Bieres() {
		super();
	}

}
