package com.example.purchasingscrapapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.purchasingscrapapp.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonScrapList, buttonSearchScrap, buttonPostScrap, buttonChat, buttonEvaluateScrap, buttonTrackScrap, buttonAnalyzeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonScrapList = findViewById(R.id.buttonScrapList);
        buttonSearchScrap = findViewById(R.id.buttonSearchScrap);
        buttonPostScrap = findViewById(R.id.buttonPostScrap);
        buttonChat = findViewById(R.id.buttonChat);
        buttonEvaluateScrap = findViewById(R.id.buttonEvaluateScrap);
        buttonTrackScrap = findViewById(R.id.buttonTrackScrap);
        buttonAnalyzeData = findViewById(R.id.buttonAnalyzeData);

        buttonScrapList.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ScrapListActivity.class)));

//        buttonSearchScrap.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, SearchScrapActivity.class)));
//
//        buttonPostScrap.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, PostScrapActivity.class)));
//
//        buttonChat.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, ChatActivity.class)));
//
//        buttonEvaluateScrap.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, EvaluateScrapActivity.class)));
//
//        buttonTrackScrap.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, TrackScrapActivity.class)));
//
//        buttonAnalyzeData.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, AnalyzeDataActivity.class)));
    }
}
