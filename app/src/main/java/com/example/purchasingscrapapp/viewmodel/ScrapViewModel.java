package com.example.purchasingscrapapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.model.ScrapCategory;
import com.example.purchasingscrapapp.repository.ScrapRepository;

import java.util.List;

public class ScrapViewModel extends ViewModel {

    private ScrapRepository scrapRepository;
    private LiveData<List<Scrap>> allScraps;
    private LiveData<List<ScrapCategory>> scrapCategories;

    public ScrapViewModel() {
        scrapRepository = new ScrapRepository();
        allScraps = scrapRepository.getAllScraps();
        scrapCategories = scrapRepository.getScrapCategories();
    }

    public LiveData<List<Scrap>> getAllScraps() {
        return allScraps;
    }

    public LiveData<List<ScrapCategory>> getScrapCategories() {
        return scrapCategories;
    }

    public LiveData<Boolean> postScrap(Scrap scrap) {
        return scrapRepository.postScrap(scrap);
    }

    public LiveData<Boolean> updateScrap(Scrap scrap) {
        return scrapRepository.updateScrap(scrap);
    }

    public LiveData<Boolean> deleteScrap(String scrapId) {
        return scrapRepository.deleteScrap(scrapId);
    }
}
