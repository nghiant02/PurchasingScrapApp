package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.adapter.ScrapAdapter;
import com.example.purchasingscrapapp.viewmodel.ScrapViewModel;

public class ScrapListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewScraps;
    private ScrapViewModel scrapViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_list);

        recyclerViewScraps = findViewById(R.id.recyclerViewScraps);
        progressBar = findViewById(R.id.progressBar);

        recyclerViewScraps.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewScraps.setHasFixedSize(true);

        scrapViewModel = new ViewModelProvider(this).get(ScrapViewModel.class);

        progressBar.setVisibility(View.VISIBLE);
        scrapViewModel.getScraps().observe(this, scraps -> {
            progressBar.setVisibility(View.GONE);
            if (scraps != null && !scraps.isEmpty()) {
                ScrapAdapter adapter = new ScrapAdapter(scraps);
                recyclerViewScraps.setAdapter(adapter);
            } else {
                recyclerViewScraps.setAdapter(null);
            }
        });
    }
}
