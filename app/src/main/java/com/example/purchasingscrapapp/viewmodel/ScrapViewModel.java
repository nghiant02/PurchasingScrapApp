//package com.example.purchasingscrapapp.viewmodel;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.ViewModel;
//import com.example.purchasingscrapapp.model.Scrap;
//import com.example.purchasingscrapapp.model.ScrapCategory;
//import com.example.purchasingscrapapp.repository.ScrapRepository;
//
//import java.util.List;
//
//public class ScrapViewModel extends ViewModel {
//    private ScrapRepository scrapRepository;
//    private LiveData<List<Scrap>> scraps;
//    private LiveData<List<ScrapCategory>> scrapCategories;
//
//    public ScrapViewModel() {
//        scrapRepository = new ScrapRepository();
//        scraps = scrapRepository.getScraps();
//        scrapCategories = scrapRepository.getScrapCategories();
//    }
//
//    public LiveData<List<Scrap>> getScraps() {
//        return scraps;
//    }
//
//    public LiveData<List<ScrapCategory>> getScrapCategories() {
//        return scrapCategories;
//    }
//}
