package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.adapter.ScrapAdapter;
import com.example.purchasingscrapapp.viewmodel.ScrapViewModel;

public class ManageScrapPostsActivity extends AppCompatActivity {

    private ScrapViewModel scrapViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_scrap_posts);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewScrapPosts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ScrapAdapter adapter = new ScrapAdapter(scrap -> {
            // Handle click event
        });
        recyclerView.setAdapter(adapter);

        scrapViewModel = new ViewModelProvider(this).get(ScrapViewModel.class);
        scrapViewModel.getAllScraps().observe(this, scraps -> {
            adapter.setScraps(scraps);
        });
    }
}
