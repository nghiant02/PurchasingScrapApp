package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.adapter.UserAdapter;
import com.example.purchasingscrapapp.viewmodel.UserViewModel;

public class ManageUsersActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, users -> {
            adapter.setUsers(users);
        });
    }
}
