package com.example.purchasingscrapapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.repository.ScrapRepository;
import java.util.List;

public class ScrapViewModel extends ViewModel {
    private ScrapRepository scrapRepository;

    public ScrapViewModel() {
        scrapRepository = new ScrapRepository();
    }

    public LiveData<List<Scrap>> getScraps() {
        return scrapRepository.getScraps();
    }
}
