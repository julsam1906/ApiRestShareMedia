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
import com.sharemedia.restservices.dao.ZeroDechetDao;
import com.sharemedia.restservices.model.ZeroDechet;

@Repository
public class ZeroDechetDaoImpl implements ZeroDechetDao {

	private Log log = LogFactory.getLog(ZeroDechetDaoImpl.class);

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
	public void saveZero(ZeroDechet zero) {
		getFirebase();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		DatabaseReference films = database.child("zeros");

		films.push().setValueAsync(new ZeroDechet(zero.getTitre(), zero.getImage(), zero.getAstuce(),
				zero.getIngredients(), zero.getRecette()));

	}

	@Override
	public void removeZero(String titre) {
		getFirebase();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		database.child("zeros").addListenerForSingleValueEvent(new ValueEventListener() {

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
	public void updateZero(ZeroDechet zero) {
		getFirebase();
		String key = zero.getKey();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		DatabaseReference zeros = database.child("zeros");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key + "/titre", zero.getTitre());
		map.put(key + "/astuce", zero.getAstuce());
		map.put(key + "/recette", zero.getRecette());
		map.put(key + "/image", zero.getImage());
		map.put(key + "/ingredients", zero.getIngredients());
		zeros.updateChildrenAsync(map);

	}

	@Override
	public Map<String, ZeroDechet> getAll() {
		log.info("Debut de getAll Dao");
		getFirebase();
		Map<String, ZeroDechet> films = new HashMap<>();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		database.child("zeros").addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot snap : snapshot.getChildren()) {
					ZeroDechet zero = snap.getValue(ZeroDechet.class);
					zero.setKey(snap.getKey());
					films.put(snap.getKey(), zero);
				}
			}

			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub

			}
		});
		log.info("Fin de getAll dao");
		return films;
	}

}
