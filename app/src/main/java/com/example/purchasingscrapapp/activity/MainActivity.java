package com.example.purchasingscrapapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.purchasingscrapapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        findViewById(R.id.buttonViewScrapList).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ScrapListActivity.class)));
//
//        findViewById(R.id.buttonSearchScrap).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SearchScrapActivity.class)));
//
//        findViewById(R.id.buttonPostScrap).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PostScrapActivity.class)));
//
//        findViewById(R.id.buttonChat).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ChatActivity.class)));
//
//        findViewById(R.id.buttonPricing).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PricingActivity.class)));
//
//        findViewById(R.id.buttonOrderManagement).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OrderManagementActivity.class)));
//
//        findViewById(R.id.buttonAnalytics).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AnalyticsActivity.class)));
    }
}
