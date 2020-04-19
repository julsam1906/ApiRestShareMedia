package com.sharemedia.restservices.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

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
import com.sharemedia.restservices.dao.FilmDao;
import com.sharemedia.restservices.model.Film;

@Repository
public class FilmDaoImpl implements FilmDao {

	private Log log = LogFactory.getLog(FilmDaoImpl.class);

	private void getFirebase() {

		FirebaseOptions options = null;
		try {
			final KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(new SecureRandom(new byte[] { 1, 2, 3 }));
			final SecretKey key = kg.generateKey();

			final Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, key);
			InputStream is = getClass().getResourceAsStream("/firebase.json");

			CipherInputStream output = new CipherInputStream(is, c);

			options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(output))
					.setDatabaseUrl("https://app-julien-java.firebaseio.com").build();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp app = FirebaseApp.initializeApp(options);
			FirebaseDatabase.getInstance(app);
		}
	}

	@Override
	public void saveData(Film film) {

		getFirebase();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		DatabaseReference films = database.child("films");

		films.push().setValueAsync(new Film(film.getTitle(), film.getPlateform(), film.getUrl(), film.getDescriptif()));

	}

	@Override
	public void removeData(String title) {
		getFirebase();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		database.child("films").addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot snap : snapshot.getChildren()) {
					for (DataSnapshot sn : snap.getChildren()) {
						if (sn.getValue(String.class).equals(title)) {
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
	public Map<String, Film> getAll() {
		log.info("Debut de getAll Dao");
		getFirebase();
		Map<String, Film> films = new HashMap<>();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		database.child("films").addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot snap : snapshot.getChildren()) {
					Film film = snap.getValue(Film.class);
					film.setKey(snap.getKey());
					films.put(snap.getKey(), film);
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

	@Override
	public void updateData(Film film, String key) {
		getFirebase();
		DatabaseReference database = FirebaseDatabase.getInstance().getReference();
		DatabaseReference films = database.child("films");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key + "/title", film.getTitle());
		map.put(key + "/plateform", film.getPlateform());
		map.put(key + "/url", film.getUrl());
		map.put(key + "/descriptif", film.getDescriptif());
		films.updateChildrenAsync(map);

	}

}
