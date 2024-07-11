package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.adapter.UserAdapter;
import com.example.purchasingscrapapp.model.User;
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

        adapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onBlockUnblockClick(User user) {
                String newStatus = user.getStatus().equals("active") ? "blocked" : "active";
                user.setStatus(newStatus);
                userViewModel.updateUserStatus(user);
                Toast.makeText(ManageUsersActivity.this, "User status updated to " + newStatus, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(User user) {
                // Handle user details view
                // You can start a new activity to show user details
            }
        });
    }
}
