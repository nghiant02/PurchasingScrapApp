package com.example.purchasingscrapapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.model.ScrapCategory;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ScrapRepository {
    private final FirebaseFirestore db;
    private final CollectionReference scrapsRef;
    private final CollectionReference categoriesRef;

    public ScrapRepository() {
        db = FirebaseFirestore.getInstance();
        scrapsRef = db.collection("scraps");
        categoriesRef = db.collection("scrap_categories");
    }

    public LiveData<List<Scrap>> getScraps() {
        MutableLiveData<List<Scrap>> scrapsLiveData = new MutableLiveData<>();
        scrapsRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Scrap> scrapList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Scrap scrap = document.toObject(Scrap.class);
                    scrapList.add(scrap);
                }
                scrapsLiveData.setValue(scrapList);
            }
        });
        return scrapsLiveData;
    }

    public LiveData<List<ScrapCategory>> getScrapCategories() {
        MutableLiveData<List<ScrapCategory>> categoriesLiveData = new MutableLiveData<>();
        categoriesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<ScrapCategory> categoryList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    ScrapCategory category = document.toObject(ScrapCategory.class);
                    categoryList.add(category);
                }
                categoriesLiveData.setValue(categoryList);
            }
        });
        return categoriesLiveData;
    }
}
