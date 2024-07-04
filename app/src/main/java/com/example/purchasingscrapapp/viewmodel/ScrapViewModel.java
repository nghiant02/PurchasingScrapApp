package com.example.purchasingscrapapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.repository.ScrapRepository;

import java.util.List;

public class ScrapViewModel extends ViewModel {

    private ScrapRepository scrapRepository;
    private LiveData<List<Scrap>> scrapListLiveData;
    private LiveData<List<Scrap>> filteredScrapListLiveData;

    public ScrapViewModel() {
        scrapRepository = new ScrapRepository();
        scrapListLiveData = scrapRepository.getScrapList();
        filteredScrapListLiveData = scrapRepository.getFilteredScrapList();
    }

    public LiveData<List<Scrap>> getScrapList() {
        return scrapListLiveData;
    }

    public void fetchScraps() {
        scrapRepository.fetchScraps();
    }

    public LiveData<List<Scrap>> getFilteredScrapList() {
        return filteredScrapListLiveData;
    }

    public void searchAndFilterScraps(String query, String type, String location) {
        scrapRepository.searchAndFilterScraps(query, type, location);
    }
}
