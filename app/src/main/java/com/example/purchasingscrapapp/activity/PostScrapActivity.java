package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.Scrap;
import com.example.purchasingscrapapp.viewmodel.ScrapViewModel;

public class PostScrapActivity extends AppCompatActivity {

    private ScrapViewModel scrapViewModel;
    private EditText editTextName, editTextDescription, editTextLocation;
    private Button buttonPostScrap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_scrap);

        editTextName = findViewById(R.id.edit_text_scrap_name);
        editTextDescription = findViewById(R.id.edit_text_scrap_description);
        editTextLocation = findViewById(R.id.edit_text_scrap_location);
        buttonPostScrap = findViewById(R.id.button_post_scrap);

        scrapViewModel = new ViewModelProvider(this).get(ScrapViewModel.class);

        buttonPostScrap.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String description = editTextDescription.getText().toString();
            String location = editTextLocation.getText().toString();

            Scrap scrap = new Scrap();
            scrap.setName(name);
            scrap.setDescription(description);
            scrap.setLocation(location);
            // Add image URL handling here if needed

            scrapViewModel.postScrap(scrap);
            finish(); // Close the activity
        });
    }
}
