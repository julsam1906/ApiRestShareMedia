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
import com.sharemedia.restservices.dao.BieresDao;
import com.sharemedia.restservices.model.Bieres;

@Repository
public class BieresDaoImpl implements BieresDao {

	private Log log = LogFactory.getLog(BieresDaoImpl.class);

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
	public void saveBiere(Bieres beer) {
		getFirebase();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		DatabaseReference films = database.child("beers");

		films.push().setValueAsync(new Bieres(beer.getBrasserie(), beer.getShop(), beer.getImage(),
				beer.getHoublons(), beer.getDescriptif(), beer.getTitre()));

	}

	@Override
	public void removeBiere(String titre) {
		getFirebase();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		database.child("beers").addListenerForSingleValueEvent(new ValueEventListener() {

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
	public void updateBiere(Bieres beer) {
		getFirebase();
		String key = beer.getKey();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		DatabaseReference films = database.child("beers");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key + "/titre", beer.getTitre());
		map.put(key + "/houblons", beer.getHoublons());
		map.put(key + "/shop", beer.getShop());
		map.put(key + "/descriptif", beer.getDescriptif());
		map.put(key + "/image", beer.getImage());
		map.put(key + "/brasserie", beer.getBrasserie());
		films.updateChildrenAsync(map);

	}

	@Override
	public Map<String, Bieres> getAll() {
		log.info("Debut de getAll Dao");
		getFirebase();
		Map<String, Bieres> beers = new HashMap<>();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		database.child("beers").addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot snap : snapshot.getChildren()) {
					Bieres beer = snap.getValue(Bieres.class);
					beer.setKey(snap.getKey());
					beers.put(snap.getKey(), beer);
				}
			}

			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub

			}
		});
		log.info("Fin de getAll dao");
		return beers;
	}

}
