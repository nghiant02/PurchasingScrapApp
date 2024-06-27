package com.example.purchasingscrapapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.repository.ScrapRepository;

import java.util.List;

public class ScrapViewModel extends ViewModel {
    private final ScrapRepository scrapRepository;
    private final MutableLiveData<List<Scrap>> scrapsLiveData = new MutableLiveData<>();

    public ScrapViewModel() {
        scrapRepository = new ScrapRepository();
        loadScraps();
    }

    public LiveData<List<Scrap>> getScraps() {
        return scrapsLiveData;
    }

    private void loadScraps() {
        scrapRepository.getScrapList(new ScrapRepository.ScrapListCallback() {
            @Override
            public void onScrapListLoaded(List<Scrap> scraps) {
                scrapsLiveData.setValue(scraps);
            }

            @Override
            public void onError(Exception e) {
                // Handle error
            }
        });
    }

    public void searchScrap(String keyword) {
        scrapRepository.searchScrap(keyword, new ScrapRepository.ScrapListCallback() {
            @Override
            public void onScrapListLoaded(List<Scrap> scraps) {
                scrapsLiveData.setValue(scraps);
            }

            @Override
            public void onError(Exception e) {
                // Handle error
            }
        });
    }

    public void filterScrap(String category, String location) {
        scrapRepository.filterScrap(category, location, new ScrapRepository.ScrapListCallback() {
            @Override
            public void onScrapListLoaded(List<Scrap> scraps) {
                scrapsLiveData.setValue(scraps);
            }

            @Override
            public void onError(Exception e) {
                // Handle error
            }
        });
    }
}
