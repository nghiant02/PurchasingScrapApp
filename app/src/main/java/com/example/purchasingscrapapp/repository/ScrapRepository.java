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

    public ScrapRepository() {
        firestore = FirebaseFirestore.getInstance();
        scrapListLiveData = new MutableLiveData<>();
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
}
