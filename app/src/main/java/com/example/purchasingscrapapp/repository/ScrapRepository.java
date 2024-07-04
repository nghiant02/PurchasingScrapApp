package com.example.purchasingscrapapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.purchasingscrapapp.model.Scrap;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ScrapRepository {

    private FirebaseFirestore firestore;
    private MutableLiveData<List<Scrap>> scrapListLiveData;
    private MutableLiveData<List<Scrap>> filteredScrapListLiveData;

    public ScrapRepository() {
        firestore = FirebaseFirestore.getInstance();
        scrapListLiveData = new MutableLiveData<>();
        filteredScrapListLiveData = new MutableLiveData<>();
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
}
