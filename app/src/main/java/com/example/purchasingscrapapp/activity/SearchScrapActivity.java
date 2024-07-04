package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.adapter.ScrapAdapter;
import com.example.purchasingscrapapp.viewmodel.ScrapViewModel;

public class SearchScrapActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Spinner typeSpinner, locationSpinner;
    private Button searchButton;
    private RecyclerView recyclerView;
    private ScrapAdapter scrapAdapter;
    private ScrapViewModel scrapViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_scrap);

        searchEditText = findViewById(R.id.editTextSearch);
        typeSpinner = findViewById(R.id.spinnerType);
        locationSpinner = findViewById(R.id.spinnerLocation);
        searchButton = findViewById(R.id.buttonSearch);
        recyclerView = findViewById(R.id.recyclerViewScrap);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scrapAdapter = new ScrapAdapter();
        recyclerView.setAdapter(scrapAdapter);

        scrapViewModel = new ViewModelProvider(this).get(ScrapViewModel.class);
        scrapViewModel.getFilteredScrapList().observe(this, scraps -> scrapAdapter.setScrapList(scraps));

        searchButton.setOnClickListener(v -> performSearch());
    }

    private void performSearch() {
        String query = searchEditText.getText().toString().trim();
        String type = typeSpinner.getSelectedItem().toString();
        String location = locationSpinner.getSelectedItem().toString();

        scrapViewModel.searchAndFilterScraps(query, type, location);
    }
}
