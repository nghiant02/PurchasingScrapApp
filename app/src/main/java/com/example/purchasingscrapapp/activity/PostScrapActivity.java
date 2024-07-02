//package com.example.purchasingscrapapp.activity;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import com.example.purchasingscrapapp.R;
//import com.example.purchasingscrapapp.model.Scrap;
//import com.example.purchasingscrapapp.repository.ScrapRepository;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import java.util.UUID;
//
//public class PostScrapActivity extends AppCompatActivity {
//
//    private static final int PICK_IMAGE_REQUEST = 1;
//
//    private EditText nameEditText, descriptionEditText, priceEditText, quantityEditText, unitEditText, locationEditText;
//    private ImageView scrapImageView;
//    private Uri imageUri;
//    private ProgressBar progressBar;
//    private ScrapRepository scrapRepository;
//    private FirebaseStorage storage;
//    private FirebaseAuth auth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_post_scrap);
//
//        auth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = auth.getCurrentUser();
//
//        if (currentUser == null) {
//            // Redirect to login screen if user is not authenticated
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }
//
//        nameEditText = findViewById(R.id.editTextName);
//        descriptionEditText = findViewById(R.id.editTextDescription);
//        priceEditText = findViewById(R.id.editTextReferencePrice);
//        quantityEditText = findViewById(R.id.editTextQuantity);
//        unitEditText = findViewById(R.id.editTextUnit);
//        locationEditText = findViewById(R.id.editTextLocation);
//        scrapImageView = findViewById(R.id.scrapImageView);
//        progressBar = findViewById(R.id.progressBar);
//
//        scrapRepository = new ScrapRepository();
//        storage = FirebaseStorage.getInstance();
//
//        findViewById(R.id.chooseImageButton).setOnClickListener(v -> chooseImage());
//        findViewById(R.id.postScrapButton).setOnClickListener(v -> postScrap());
//    }
//
//    private void chooseImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
//            imageUri = data.getData();
//            scrapImageView.setImageURI(imageUri);
//        }
//    }
//
//    private void postScrap() {
//        String name = nameEditText.getText().toString().trim();
//        String description = descriptionEditText.getText().toString().trim();
//        String priceString = priceEditText.getText().toString().trim();
//        String quantityString = quantityEditText.getText().toString().trim();
//        String unit = unitEditText.getText().toString().trim();
//        String location = locationEditText.getText().toString().trim();
//
//        if (name.isEmpty() || description.isEmpty() || priceString.isEmpty() || quantityString.isEmpty() || unit.isEmpty() || location.isEmpty() || imageUri == null) {
//            Toast.makeText(this, "Please fill all fields and choose an image", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        double price;
//        int quantity;
//        try {
//            price = Double.parseDouble(priceString);
//            quantity = Integer.parseInt(quantityString);
//        } catch (NumberFormatException e) {
//            Toast.makeText(this, "Invalid price or quantity", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        progressBar.setVisibility(View.VISIBLE);
//        StorageReference storageReference = storage.getReference("scrap_images/" + UUID.randomUUID().toString());
//        storageReference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
//            Scrap scrap = new Scrap(
//                    UUID.randomUUID().toString(),
//                    name,
//                    description,
//                    uri.toString(),
//                    price,
//                    quantity,
//                    unit,
//                    location,
//                    "available",
//                    System.currentTimeMillis(),
//                    System.currentTimeMillis()
//            );
//            scrapRepository.postScrap(scrap);
//            progressBar.setVisibility(View.GONE);
//            finish();
//        })).addOnFailureListener(e -> {
//            progressBar.setVisibility(View.GONE);
//            Toast.makeText(this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//        });
//    }
//}
