package com.example.purchasingscrapapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.utils.FirebaseUtils;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button buttonViewScraps, buttonPostScrap, buttonProfile, buttonLogout;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        // Initialize buttons
        buttonViewScraps = findViewById(R.id.buttonViewScraps);
        buttonPostScrap = findViewById(R.id.buttonPostScrap);
        buttonProfile = findViewById(R.id.buttonProfile);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Set onClick listeners
//        buttonViewScraps.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ScrapListActivity.class)));
//        buttonPostScrap.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PostScrapActivity.class)));
//        buttonProfile.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
        buttonLogout.setOnClickListener(v -> logoutUser());

        // Check if user is logged in
        if (!FirebaseUtils.isLoggedIn()) {
            redirectToLogin();
        }
    }

    private void logoutUser() {
        auth.signOut();
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        redirectToLogin();
    }

    private void redirectToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
