package com.example.purchasingscrapapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.viewmodel.ScrapViewModel;

public class PostScrapActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextScrapName, editTextScrapDescription, editTextScrapLocation;
    private Spinner spinnerCategory;
    private ImageView imageViewScrap;
    private Button buttonSelectImage, buttonPostScrap;
    private ProgressBar progressBar;

    private Uri imageUri;
    private ScrapViewModel scrapViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_scrap);

        editTextScrapName = findViewById(R.id.editTextScrapName);
        editTextScrapDescription = findViewById(R.id.editTextScrapDescription);
        editTextScrapLocation = findViewById(R.id.editTextScrapLocation);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        imageViewScrap = findViewById(R.id.imageViewScrap);
        buttonSelectImage = findViewById(R.id.buttonSelectImage);
        buttonPostScrap = findViewById(R.id.buttonPostScrap);
        progressBar = findViewById(R.id.progressBar);

        scrapViewModel = new ViewModelProvider(this).get(ScrapViewModel.class);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.scrap_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        buttonSelectImage.setOnClickListener(v -> openFileChooser());
        buttonPostScrap.setOnClickListener(v -> postScrap());
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageViewScrap.setImageURI(imageUri);
        }
    }

    private void postScrap() {
        String name = editTextScrapName.getText().toString().trim();
        String description = editTextScrapDescription.getText().toString().trim();
        String location = editTextScrapLocation.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();

        if (name.isEmpty() || description.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Scrap scrap = new Scrap();
        scrap.setName(name);
        scrap.setDescription(description);
        scrap.setLocation(location);
        scrap.setCategoryId(category);

        progressBar.setVisibility(View.VISIBLE);

        scrapViewModel.postScrap(scrap, imageUri).observe(this, success -> {
            progressBar.setVisibility(View.GONE);
            if (success) {
                Toast.makeText(PostScrapActivity.this, "Scrap posted successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(PostScrapActivity.this, "Failed to post scrap", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
