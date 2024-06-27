package com.example.purchasingscrapapp.repository;

import com.example.purchasingscrapapp.model.Scrap;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ScrapRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public interface ScrapListCallback {
        void onScrapListLoaded(List<Scrap> scraps);
        void onError(Exception e);
    }

    public void getScrapList(final ScrapListCallback callback) {
        db.collection("scraps").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Scrap> scrapList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Scrap scrap = document.toObject(Scrap.class);
                    scrapList.add(scrap);
                }
                callback.onScrapListLoaded(scrapList);
            } else {
                callback.onError(task.getException());
            }
        });
    }

    public void searchScrap(String keyword, final ScrapListCallback callback) {
        db.collection("scraps").whereEqualTo("name", keyword).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Scrap> scrapList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Scrap scrap = document.toObject(Scrap.class);
                    scrapList.add(scrap);
                }
                callback.onScrapListLoaded(scrapList);
            } else {
                callback.onError(task.getException());
            }
        });
    }

    public void filterScrap(String category, String location, final ScrapListCallback callback) {
        db.collection("scraps").whereEqualTo("category", category).whereEqualTo("location", location).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Scrap> scrapList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Scrap scrap = document.toObject(Scrap.class);
                    scrapList.add(scrap);
                }
                callback.onScrapListLoaded(scrapList);
            } else {
                callback.onError(task.getException());
            }
        });
    }

    public void postScrap(Scrap scrap) {
        db.collection("scraps").add(scrap).addOnSuccessListener(documentReference -> {
            // Handle success
        }).addOnFailureListener(e -> {
            // Handle failure
        });
    }
}
