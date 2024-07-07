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

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference scrapsRef = db.collection("scraps");
    private CollectionReference categoriesRef = db.collection("scrap_categories");

    public LiveData<List<Scrap>> getScrapsByUser(String userId) {
        MutableLiveData<List<Scrap>> scrapsData = new MutableLiveData<>();
        scrapsRef.whereEqualTo("userId", userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Scrap> scraps = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Scrap scrap = document.toObject(Scrap.class);
                    scrap.setId(document.getId());
                    scraps.add(scrap);
                }
                scrapsData.setValue(scraps);
            }
        });
        return scrapsData;
    }

    public LiveData<Boolean> postScrap(Scrap scrap) {
        MutableLiveData<Boolean> successData = new MutableLiveData<>();
        scrap.setCreatedAt(System.currentTimeMillis());
        scrapsRef.add(scrap).addOnCompleteListener(task -> successData.setValue(task.isSuccessful()));
        return successData;
    }

    public LiveData<List<ScrapCategory>> getScrapCategories() {
        MutableLiveData<List<ScrapCategory>> categoriesData = new MutableLiveData<>();
        categoriesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<ScrapCategory> categories = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    ScrapCategory category = document.toObject(ScrapCategory.class);
                    category.setId(document.getId());
                    categories.add(category);
                }
                categoriesData.setValue(categories);
            }
        });
        return categoriesData;
    }

    public LiveData<Boolean> updateScrap(Scrap scrap) {
        MutableLiveData<Boolean> successData = new MutableLiveData<>();
        scrap.setUpdatedAt(System.currentTimeMillis());
        scrapsRef.document(scrap.getId()).set(scrap).addOnCompleteListener(task -> successData.setValue(task.isSuccessful()));
        return successData;
    }

    public LiveData<Boolean> deleteScrap(String scrapId) {
        MutableLiveData<Boolean> successData = new MutableLiveData<>();
        scrapsRef.document(scrapId).delete().addOnCompleteListener(task -> successData.setValue(task.isSuccessful()));
        return successData;
    }

    public LiveData<List<Scrap>> getAllScraps() {
        MutableLiveData<List<Scrap>> scrapsData = new MutableLiveData<>();
        scrapsRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Scrap> scraps = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Scrap scrap = document.toObject(Scrap.class);
                    scrap.setId(document.getId());
                    scraps.add(scrap);
                }
                scrapsData.setValue(scraps);
            }
        });
        return scrapsData;
    }
}
