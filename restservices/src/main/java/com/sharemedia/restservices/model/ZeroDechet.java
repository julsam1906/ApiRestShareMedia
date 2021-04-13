package com.sharemedia.restservices.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zerodechet")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ZeroDechet {

	@Id
	private String key;
	private String titre;
	private String image;
	private String recette;
	private String astuce;
	private String ingredients;
	
}
