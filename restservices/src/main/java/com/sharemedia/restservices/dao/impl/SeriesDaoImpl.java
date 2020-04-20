package com.sharemedia.restservices.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sharemedia.restservices.dao.SeriesDao;
import com.sharemedia.restservices.model.Series;

@Repository
public class SeriesDaoImpl implements SeriesDao {

	private Log log = LogFactory.getLog(SeriesDaoImpl.class);

	private void getFirebase() {

		FirebaseOptions options = null;
		try {

			InputStream is = getClass().getResourceAsStream("/firebase.json");

			options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(is))
					.setDatabaseUrl("https://app-julien-java.firebaseio.com").build();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp app = FirebaseApp.initializeApp(options);
			FirebaseDatabase.getInstance(app);
		}
	}

	@Override
	public void saveSerie(Series serie) {
		getFirebase();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		DatabaseReference films = database.child("series");

		films.push().setValueAsync(
				new Series(serie.getTitre(), serie.getPlateforme(), serie.getLien(), serie.getDescriptif()));

	}

	@Override
	public void removeSerie(String titre) {
		getFirebase();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		database.child("series").addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot snap : snapshot.getChildren()) {
					for (DataSnapshot sn : snap.getChildren()) {
						if (sn.getValue(String.class).equals(titre)) {
							snap.getRef().setValueAsync(null);
							break;
						}
					}
				}

			}

			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void updateSerie(Series serie) {
		getFirebase();
		String key = serie.getKey();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		DatabaseReference films = database.child("series");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key + "/titre", serie.getTitre());
		map.put(key + "/lien", serie.getLien());
		map.put(key + "/descriptif", serie.getDescriptif());
		map.put(key + "/plateforme", serie.getPlateforme());
		films.updateChildrenAsync(map);

	}

	@Override
	public Map<String, Series> getAll() {
		log.info("Debut de getAll Dao");
		getFirebase();
		Map<String, Series> series = new HashMap<>();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		database.child("series").addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot snap : snapshot.getChildren()) {
					Series serie = snap.getValue(Series.class);
					serie.setKey(snap.getKey());
					series.put(snap.getKey(), serie);
				}
			}

			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub

			}
		});
		log.info("Fin de getAll dao");
		return series;
	}

}
