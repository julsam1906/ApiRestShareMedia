package com.sharemedia.restservices.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "film")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Film {

	@Id
	private String key;
	private String title;
	private String plateform;
	private String url;
	private String descriptif;


}
