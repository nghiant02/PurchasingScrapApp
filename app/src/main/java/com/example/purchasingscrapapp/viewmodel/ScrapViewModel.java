package com.example.purchasingscrapapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.model.ScrapCategory;
import com.example.purchasingscrapapp.repository.ScrapRepository;

import java.util.List;

public class ScrapViewModel extends ViewModel {
    private final ScrapRepository scrapRepository;
    private final LiveData<List<Scrap>> scraps;
    private final LiveData<List<ScrapCategory>> categories;

    public ScrapViewModel() {
        scrapRepository = new ScrapRepository();
        scraps = scrapRepository.getScraps();
        categories = scrapRepository.getScrapCategories();
    }

    public LiveData<List<Scrap>> getScraps() {
        return scraps;
    }

    public LiveData<List<ScrapCategory>> getScrapCategories() {
        return categories;
    }
}
