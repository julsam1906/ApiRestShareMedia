package com.sharemedia.restservices.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beers")
@Getter @Setter @NoArgsConstructor @ToString
public class Bieres {

	private String brasserie;
	private String shop;
	private String image;
	private String houblons;
	private String descriptif;
	private String titre;
	@Id
	@GeneratedValue
	private String key;

}
