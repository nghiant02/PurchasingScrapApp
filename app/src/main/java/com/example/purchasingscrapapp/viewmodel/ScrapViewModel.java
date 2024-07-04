package com.example.purchasingscrapapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.repository.ScrapRepository;

import java.util.List;

public class ScrapViewModel extends ViewModel {

    private ScrapRepository scrapRepository;
    private LiveData<List<Scrap>> scrapListLiveData;

    public ScrapViewModel() {
        scrapRepository = new ScrapRepository();
        scrapListLiveData = scrapRepository.getScrapList();
    }

    public LiveData<List<Scrap>> getScrapList() {
        return scrapListLiveData;
    }

    public void fetchScraps() {
        scrapRepository.fetchScraps();
    }
}
