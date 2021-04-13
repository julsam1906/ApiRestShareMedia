package com.sharemedia.restservices.dao;

import java.util.List;
import java.util.Map;

import com.sharemedia.restservices.model.Bieres;
import com.sharemedia.restservices.model.ZeroDechet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

public interface BieresDao {

	void saveBiere(Bieres bieres);

	 void removeBiere(String key);

	 void updateBiere(Bieres bieres);

	 List<Bieres> getAll();
	



}
