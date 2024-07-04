package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.fragment.ScrapListFragment;

public class ScrapListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrap_list);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ScrapListFragment())
                    .commit();
        }
    }
}
