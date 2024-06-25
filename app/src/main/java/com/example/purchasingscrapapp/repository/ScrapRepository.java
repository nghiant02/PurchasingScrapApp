//package com.example.purchasingscrapapp.repository;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import com.example.purchasingscrapapp.model.Scrap;
//import com.example.purchasingscrapapp.model.ScrapCategory;
//import com.example.purchasingscrapapp.utils.Constants;
//import com.example.purchasingscrapapp.utils.FirebaseUtils;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ScrapRepository {
//    private final CollectionReference scrapsRef;
//    private final CollectionReference scrapCategoriesRef;
//
//    public ScrapRepository() {
//        scrapsRef = FirebaseUtils.getFirestoreInstance().collection(Constants.SCRAPS_COLLECTION);
//        scrapCategoriesRef = FirebaseUtils.getFirestoreInstance().collection(Constants.SCRAP_CATEGORIES_COLLECTION);
//    }
//
//    public LiveData<List<Scrap>> getScraps() {
//        MutableLiveData<List<Scrap>> scrapsLiveData = new MutableLiveData<>();
//        scrapsRef.get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                List<Scrap> scrapList = new ArrayList<>();
//                for (QueryDocumentSnapshot document : task.getResult()) {
//                    Scrap scrap = document.toObject(Scrap.class);
//                    scrapList.add(scrap);
//                }
//                scrapsLiveData.setValue(scrapList);
//            }
//        });
//        return scrapsLiveData;
//    }
//
//    public LiveData<List<ScrapCategory>> getScrapCategories() {
//        MutableLiveData<List<ScrapCategory>> scrapCategoriesLiveData = new MutableLiveData<>();
//        scrapCategoriesRef.get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                List<ScrapCategory> scrapCategoryList = new ArrayList<>();
//                for (QueryDocumentSnapshot document : task.getResult()) {
//                    ScrapCategory scrapCategory = document.toObject(ScrapCategory.class);
//                    scrapCategoryList.add(scrapCategory);
//                }
//                scrapCategoriesLiveData.setValue(scrapCategoryList);
//            }
//        });
//        return scrapCategoriesLiveData;
//    }
//}
