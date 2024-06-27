package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.adapter.ScrapAdapter;
import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.repository.ScrapRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchScrapActivity extends AppCompatActivity {

    private EditText searchEditText;
    private ProgressBar progressBar;
    private RecyclerView recyclerViewScraps;
    private ScrapAdapter scrapAdapter;
    private ScrapRepository scrapRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_scrap);

        searchEditText = findViewById(R.id.searchEditText);
        progressBar = findViewById(R.id.progressBar);
        recyclerViewScraps = findViewById(R.id.recyclerViewScraps);

        scrapRepository = new ScrapRepository();

        scrapAdapter = new ScrapAdapter(new ArrayList<>());
        recyclerViewScraps.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewScraps.setAdapter(scrapAdapter);

        findViewById(R.id.searchButton).setOnClickListener(v -> searchScraps());
    }

    private void searchScraps() {
        String keyword = searchEditText.getText().toString().trim();
        if (!keyword.isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);
            scrapRepository.searchScrap(keyword, new ScrapRepository.ScrapListCallback() {
                @Override
                public void onScrapListLoaded(List<Scrap> scraps) {
                    progressBar.setVisibility(View.GONE);
                    scrapAdapter.setScrapList(scraps);
                }

                @Override
                public void onError(Exception e) {
                    progressBar.setVisibility(View.GONE);
                    // Handle error
                }
            });
        }
    }
}
