package com.example.purchasingscrapapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.repository.ScrapRepository;

import java.util.List;

public class ScrapViewModel extends ViewModel {

    private final ScrapRepository scrapRepository;
    private final MutableLiveData<List<Scrap>> scrapList;

    public ScrapViewModel() {
        scrapRepository = new ScrapRepository();
        scrapList = scrapRepository.getScrapList();
    }

    public LiveData<List<Scrap>> getScrapList() {
        return scrapList;
    }

    public void fetchScraps() {
        scrapRepository.getScrapList().observeForever(scrapList::setValue);
    }

    public LiveData<List<Scrap>> searchScrap(String query) {
        MutableLiveData<List<Scrap>> searchResults = new MutableLiveData<>();
        scrapRepository.searchScrap(query).observeForever(searchResults::setValue);
        return searchResults;
    }

    public void postScrap(Scrap scrap) {
        scrapRepository.postScrap(scrap);
    }

    public void updateScrap(Scrap scrap) {
        scrapRepository.updateScrap(scrap);
    }

    public void deleteScrap(Scrap scrap) {
        scrapRepository.deleteScrap(scrap);
    }

}
