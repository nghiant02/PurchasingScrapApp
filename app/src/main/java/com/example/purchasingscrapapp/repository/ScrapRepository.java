package com.example.purchasingscrapapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.purchasingscrapapp.model.Scrap;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ScrapRepository {

    private final FirebaseFirestore firestore;
    private final MutableLiveData<List<Scrap>> scrapListLiveData;

    public ScrapRepository() {
        firestore = FirebaseFirestore.getInstance();
        scrapListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Scrap>> getScrapList() {
        loadScrapData();
        return scrapListLiveData;
    }

    private void loadScrapData() {
        firestore.collection("scraps")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Scrap> scrapList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Scrap scrap = document.toObject(Scrap.class);
                            scrapList.add(scrap);
                        }
                        scrapListLiveData.setValue(scrapList);
                    }
                });
    }

    public MutableLiveData<List<Scrap>> searchScrap(String query) {
        MutableLiveData<List<Scrap>> searchResults = new MutableLiveData<>();
        firestore.collection("scraps")
                .whereEqualTo("name", query)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Scrap> scrapList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Scrap scrap = document.toObject(Scrap.class);
                            scrapList.add(scrap);
                        }
                        searchResults.setValue(scrapList);
                    }
                });
        return searchResults;
    }

    public void postScrap(Scrap scrap) {
        firestore.collection("scraps").add(scrap);
    }

    public void updateScrap(Scrap scrap) {
        firestore.collection("scraps").document(scrap.getId()).set(scrap);
    }

    public void deleteScrap(Scrap scrap) {
        firestore.collection("scraps").document(scrap.getId()).delete();
    }

}
