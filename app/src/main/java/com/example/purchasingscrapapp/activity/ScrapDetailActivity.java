package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.model.ScrapCategory;
import com.example.purchasingscrapapp.viewmodel.ScrapViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ScrapDetailActivity extends AppCompatActivity {

    private ImageView imageViewScrapDetail;
    private EditText editTextName, editTextDescription, editTextLocation;
    private Spinner spinnerCategory;
    private Button buttonUpdateScrap, buttonDeleteScrap;
    private ProgressBar progressBar;

    private Scrap scrap;
    private ScrapViewModel scrapViewModel;
    private List<ScrapCategory> scrapCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_detail);

        initViews();

        scrapViewModel = new ViewModelProvider(this).get(ScrapViewModel.class);

        scrap = (Scrap) getIntent().getSerializableExtra("scrap");
        if (scrap != null) {
            populateScrapDetails();
        }

        scrapViewModel.getScrapCategories().observe(this, categories -> {
            scrapCategories = categories;
            List<String> categoryNames = new ArrayList<>();
            for (ScrapCategory category : categories) {
                categoryNames.add(category.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategory.setAdapter(adapter);

            // Set category spinner
            if (scrap != null) {
                int position = adapter.getPosition(getCategoryNameById(scrap.getCategoryId()));
                spinnerCategory.setSelection(position);
            }
        });

        buttonUpdateScrap.setOnClickListener(v -> updateScrapDetails());
        buttonDeleteScrap.setOnClickListener(v -> deleteScrap());
    }

    private void initViews() {
        imageViewScrapDetail = findViewById(R.id.image_view_scrap_detail);
        editTextName = findViewById(R.id.edit_text_scrap_name);
        editTextDescription = findViewById(R.id.edit_text_scrap_description);
        editTextLocation = findViewById(R.id.edit_text_scrap_location);
        spinnerCategory = findViewById(R.id.spinner_scrap_category);
        buttonUpdateScrap = findViewById(R.id.button_update_scrap);
        buttonDeleteScrap = findViewById(R.id.button_delete_scrap);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void populateScrapDetails() {
        editTextName.setText(scrap.getName());
        editTextDescription.setText(scrap.getDescription());
        editTextLocation.setText(scrap.getLocation());
        if (scrap.getImageUrl() != null && !scrap.getImageUrl().isEmpty()) {
            Glide.with(this).load(scrap.getImageUrl()).into(imageViewScrapDetail);
        }
    }

    private void updateScrapDetails() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null || !currentUser.getUid().equals(scrap.getUserId())) {
            Toast.makeText(this, "You do not have permission to update this scrap", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        String location = editTextLocation.getText().toString();
        String categoryName = spinnerCategory.getSelectedItem().toString();
        String categoryId = getCategoryIdByName(categoryName);

        scrap.setName(name);
        scrap.setDescription(description);
        scrap.setLocation(location);
        scrap.setCategoryId(categoryId);
        scrap.setUpdatedAt(System.currentTimeMillis());

        // Show ProgressBar
        progressBar.setVisibility(View.VISIBLE);

        scrapViewModel.updateScrap(scrap).observe(this, success -> {
            progressBar.setVisibility(View.GONE);
            if (success) {
                Toast.makeText(ScrapDetailActivity.this, "Scrap updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(ScrapDetailActivity.this, "Failed to update scrap", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteScrap() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null || !currentUser.getUid().equals(scrap.getUserId())) {
            Toast.makeText(this, "You do not have permission to delete this scrap", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show ProgressBar
        progressBar.setVisibility(View.VISIBLE);

        scrapViewModel.deleteScrap(scrap.getId()).observe(this, success -> {
            progressBar.setVisibility(View.GONE);
            if (success) {
                Toast.makeText(ScrapDetailActivity.this, "Scrap deleted successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(ScrapDetailActivity.this, "Failed to delete scrap", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getCategoryIdByName(String categoryName) {
        for (ScrapCategory category : scrapCategories) {
            if (category.getName().equals(categoryName)) {
                return category.getId();
            }
        }
        return null;
    }

    private String getCategoryNameById(String categoryId) {
        for (ScrapCategory category : scrapCategories) {
            if (category.getId().equals(categoryId)) {
                return category.getName();
            }
        }
        return null;
    }
}
