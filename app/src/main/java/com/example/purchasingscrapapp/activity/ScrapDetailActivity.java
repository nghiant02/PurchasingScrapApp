package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.Scrap;

public class ScrapDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameTextView, descriptionTextView, priceTextView, quantityTextView, locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_detail);

        imageView = findViewById(R.id.imageView);
        nameTextView = findViewById(R.id.nameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        priceTextView = findViewById(R.id.priceTextView);
        quantityTextView = findViewById(R.id.quantityTextView);
        locationTextView = findViewById(R.id.locationTextView);

        Scrap scrap = (Scrap) getIntent().getSerializableExtra("SCRAP_DETAILS");

        if (scrap != null) {
            nameTextView.setText(scrap.getName());
            descriptionTextView.setText(scrap.getDescription());
            priceTextView.setText(String.valueOf(scrap.getReferencePrice()));
            quantityTextView.setText(String.valueOf(scrap.getQuantity()));
            locationTextView.setText(scrap.getLocation());
            Glide.with(this).load(scrap.getImageUrl()).into(imageView);
        }
    }
}
