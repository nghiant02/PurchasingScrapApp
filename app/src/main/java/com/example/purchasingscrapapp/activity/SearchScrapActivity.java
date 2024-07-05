package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.adapter.ScrapAdapter;
import com.example.purchasingscrapapp.viewmodel.ScrapViewModel;

public class SearchScrapActivity extends AppCompatActivity {

    private ScrapViewModel scrapViewModel;
    private RecyclerView recyclerView;
    private ScrapAdapter scrapAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_scrap);

        recyclerView = findViewById(R.id.recycler_view_search_scrap);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scrapAdapter = new ScrapAdapter();
        recyclerView.setAdapter(scrapAdapter);

        searchView = findViewById(R.id.search_view_scrap);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                scrapViewModel.searchScrap(query).observe(SearchScrapActivity.this, scrapList -> scrapAdapter.setScrapList(scrapList));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                scrapViewModel.searchScrap(newText).observe(SearchScrapActivity.this, scrapList -> scrapAdapter.setScrapList(scrapList));
                return false;
            }
        });

        scrapViewModel = new ViewModelProvider(this).get(ScrapViewModel.class);
    }
}
