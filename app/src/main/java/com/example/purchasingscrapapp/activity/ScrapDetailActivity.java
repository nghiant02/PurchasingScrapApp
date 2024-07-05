package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.viewmodel.ScrapViewModel;

public class ScrapDetailActivity extends AppCompatActivity {

    private ScrapViewModel scrapViewModel;
    private EditText editTextName, editTextDescription, editTextLocation;
    private Button buttonUpdateScrap, buttonDeleteScrap;
    private Scrap scrap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_detail);

        editTextName = findViewById(R.id.edit_text_scrap_name);
        editTextDescription = findViewById(R.id.edit_text_scrap_description);
        editTextLocation = findViewById(R.id.edit_text_scrap_location);
        buttonUpdateScrap = findViewById(R.id.button_update_scrap);
        buttonDeleteScrap = findViewById(R.id.button_delete_scrap);

        scrapViewModel = new ViewModelProvider(this).get(ScrapViewModel.class);

        // Assume scrap object is passed through intent
        scrap = (Scrap) getIntent().getSerializableExtra("scrap");
        if (scrap != null) {
            editTextName.setText(scrap.getName());
            editTextDescription.setText(scrap.getDescription());
            editTextLocation.setText(scrap.getLocation());
        }

        buttonUpdateScrap.setOnClickListener(v -> {
            scrap.setName(editTextName.getText().toString());
            scrap.setDescription(editTextDescription.getText().toString());
            scrap.setLocation(editTextLocation.getText().toString());
            // Add image URL handling here if needed

            scrapViewModel.updateScrap(scrap);
            finish(); // Close the activity
        });

        buttonDeleteScrap.setOnClickListener(v -> {
            scrapViewModel.deleteScrap(scrap);
            finish(); // Close the activity
        });
    }
}
