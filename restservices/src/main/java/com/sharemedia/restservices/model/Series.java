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
@Table(name = "series")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Series {

	@Id
	@GeneratedValue
	private String key;
	private String titre;
	private String plateforme;
	private String lien;
	private String descriptif;


}
