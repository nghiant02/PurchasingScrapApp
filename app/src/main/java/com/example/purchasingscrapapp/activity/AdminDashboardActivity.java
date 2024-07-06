package com.example.purchasingscrapapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.User;
import com.example.purchasingscrapapp.utils.Permission;
import com.example.purchasingscrapapp.utils.UserUtils;

public class AdminDashboardActivity extends AppCompatActivity {

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        currentUser = UserUtils.getCurrentUser();

        findViewById(R.id.button_manage_users).setOnClickListener(v -> {
            if (hasPermission(Permission.MANAGE_USERS)) {
                startActivity(new Intent(AdminDashboardActivity.this, ManageUsersActivity.class));
            } else {
                // Show a message or handle lack of permission
            }
        });

        findViewById(R.id.button_manage_scrap_posts).setOnClickListener(v -> {
            if (hasPermission(Permission.MANAGE_SCRAP_POSTS)) {
                startActivity(new Intent(AdminDashboardActivity.this, ManageScrapPostsActivity.class));
            } else {
                // Show a message or handle lack of permission
            }
        });

        findViewById(R.id.button_assign_tasks).setOnClickListener(v -> {
            if (hasPermission(Permission.ASSIGN_TASKS)) {
                startActivity(new Intent(AdminDashboardActivity.this, AssignTasksActivity.class));
            } else {
                // Show a message or handle lack of permission
            }
        });

        findViewById(R.id.button_view_reports).setOnClickListener(v -> {
            if (hasPermission(Permission.VIEW_REPORTS)) {
                startActivity(new Intent(AdminDashboardActivity.this, ViewReportsActivity.class));
            } else {
                // Show a message or handle lack of permission
            }
        });
    }

    private boolean hasPermission(String permission) {
        if (currentUser == null) return false;
        if (currentUser.getRole().equals("admin")) return true;

        // Add additional role-based logic here if needed
        return false;
    }
}
