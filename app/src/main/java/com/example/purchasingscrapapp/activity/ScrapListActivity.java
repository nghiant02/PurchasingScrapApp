package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.adapter.ScrapAdapter;
import com.example.purchasingscrapapp.viewmodel.ScrapViewModel;

public class ScrapListActivity extends AppCompatActivity {

    private ScrapViewModel scrapViewModel;
    private RecyclerView recyclerView;
    private ScrapAdapter scrapAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_list);

        recyclerView = findViewById(R.id.recycler_view_scrap_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scrapAdapter = new ScrapAdapter();
        recyclerView.setAdapter(scrapAdapter);

        scrapViewModel = new ViewModelProvider(this).get(ScrapViewModel.class);
        scrapViewModel.getScrapList().observe(this, scrapList -> scrapAdapter.setScrapList(scrapList));
    }
}
