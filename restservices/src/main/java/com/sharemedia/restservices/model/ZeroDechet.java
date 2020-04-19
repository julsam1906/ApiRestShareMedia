package com.sharemedia.restservices.model;

public class ZeroDechet {

	private String titre;
	private String image;
	private String recette;
	private String astuce;
	private String key;
	private String ingredients;
	
	
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
	 * @return the recette
	 */
	public String getRecette() {
		return recette;
	}
	/**
	 * @param recette the recette to set
	 */
	public void setRecette(String recette) {
		this.recette = recette;
	}
	/**
	 * @return the astuce
	 */
	public String getAstuce() {
		return astuce;
	}
	/**
	 * @param astuce the astuce to set
	 */
	public void setAstuce(String astuce) {
		this.astuce = astuce;
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
	 * @return the ingredients
	 */
	public String getIngredients() {
		return ingredients;
	}
	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	/**
	 * @param titre
	 * @param image
	 * @param recette
	 * @param astuce
	 * @param key
	 * @param ingredients
	 */
	public ZeroDechet(String titre, String image, String recette, String astuce, String ingredients) {
		super();
		this.titre = titre;
		this.image = image;
		this.recette = recette;
		this.astuce = astuce;
		this.ingredients = ingredients;
	}
	/**
	 * 
	 */
	public ZeroDechet() {
		super();
	}
	
	
	
	
}
