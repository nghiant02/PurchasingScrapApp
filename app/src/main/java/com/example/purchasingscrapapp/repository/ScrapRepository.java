package com.example.purchasingscrapapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.utils.FirebaseUtils;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class ScrapRepository {
    private FirebaseFirestore db = FirebaseUtils.getFirestore();

    public LiveData<List<Scrap>> getScraps() {
        MutableLiveData<List<Scrap>> scrapData = new MutableLiveData<>();
        db.collection("scraps")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Scrap> scrapList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Scrap scrap = document.toObject(Scrap.class);
                            scrapList.add(scrap);
                        }
                        scrapData.setValue(scrapList);
                    } else {
                        scrapData.setValue(null);
                    }
                });
        return scrapData;
    }
}
