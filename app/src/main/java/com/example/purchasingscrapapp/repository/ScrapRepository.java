package com.example.purchasingscrapapp.repository;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.purchasingscrapapp.model.Scrap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ScrapRepository {

    private FirebaseFirestore firestore;
    private MutableLiveData<List<Scrap>> scrapListLiveData;
    private MutableLiveData<List<Scrap>> filteredScrapListLiveData;
    private FirebaseStorage storage;

    public ScrapRepository() {
        firestore = FirebaseFirestore.getInstance();
        scrapListLiveData = new MutableLiveData<>();
        filteredScrapListLiveData = new MutableLiveData<>();
        storage = FirebaseStorage.getInstance();
    }

    public LiveData<List<Scrap>> getScrapList() {
        return scrapListLiveData;
    }

    public void fetchScraps() {
        firestore.collection("scraps").get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Scrap> scrapList = new ArrayList<>();
            queryDocumentSnapshots.forEach(document -> {
                Scrap scrap = document.toObject(Scrap.class);
                scrapList.add(scrap);
            });
            scrapListLiveData.setValue(scrapList);
        }).addOnFailureListener(e -> {
            scrapListLiveData.setValue(null);
        });
    }

    public LiveData<List<Scrap>> getFilteredScrapList() {
        return filteredScrapListLiveData;
    }

    public void searchAndFilterScraps(String query, String type, String location) {
        firestore.collection("scraps")
                .whereEqualTo("type", type)
                .whereEqualTo("location", location)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Scrap> filteredScrapList = new ArrayList<>();
                    queryDocumentSnapshots.forEach(document -> {
                        Scrap scrap = document.toObject(Scrap.class);
                        if (scrap.getName().toLowerCase().contains(query.toLowerCase())) {
                            filteredScrapList.add(scrap);
                        }
                    });
                    filteredScrapListLiveData.setValue(filteredScrapList);
                })
                .addOnFailureListener(e -> filteredScrapListLiveData.setValue(null));
    }

    public LiveData<Boolean> postScrap(Scrap scrap, Uri imageUri) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        scrap.setUserId(userId);

        if (imageUri != null) {
            String imagePath = "scrap_images/" + System.currentTimeMillis() + ".jpg";
            storage.getReference(imagePath).putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> storage.getReference(imagePath).getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                scrap.setImageUrl(uri.toString());
                                saveScrapToFirestore(scrap, result);
                            }))
                    .addOnFailureListener(e -> result.setValue(false));
        } else {
            saveScrapToFirestore(scrap, result);
        }

        return result;
    }

    private void saveScrapToFirestore(Scrap scrap, MutableLiveData<Boolean> result) {
        firestore.collection("scraps").add(scrap)
                .addOnSuccessListener(documentReference -> result.setValue(true))
                .addOnFailureListener(e -> result.setValue(false));
    }

    public LiveData<List<Scrap>> getScraps() {
        MutableLiveData<List<Scrap>> scrapsLiveData = new MutableLiveData<>();
        firestore.collection("scraps").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Scrap> scraps = queryDocumentSnapshots.toObjects(Scrap.class);
                    scrapsLiveData.setValue(scraps);
                });
        return scrapsLiveData;
    }
}
