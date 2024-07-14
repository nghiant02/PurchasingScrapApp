package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.Task;
import com.example.purchasingscrapapp.model.User;
import com.example.purchasingscrapapp.viewmodel.TaskViewModel;
import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AssignTasksActivity extends AppCompatActivity {

    private Spinner spinnerStaff, spinnerScrap;
    private Button buttonAssignTask;
    private ProgressBar progressBar;
    private TaskViewModel taskViewModel;
    private List<User> staffList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_tasks);

        spinnerStaff = findViewById(R.id.spinnerStaff);
        spinnerScrap = findViewById(R.id.spinnerScrap);
        buttonAssignTask = findViewById(R.id.buttonAssignTask);
        progressBar = findViewById(R.id.progressBar);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        taskViewModel.getAllStaff().observe(this, staff -> {
            staffList = staff;
            List<String> staffNames = new ArrayList<>();
            for (User user : staff) {
                staffNames.add(user.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, staffNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStaff.setAdapter(adapter);
        });

        buttonAssignTask.setOnClickListener(v -> assignTask());
    }

    private void assignTask() {
        int selectedStaffPosition = spinnerStaff.getSelectedItemPosition();
        if (selectedStaffPosition == -1 || staffList.isEmpty()) {
            Toast.makeText(this, "Please select a staff member", Toast.LENGTH_SHORT).show();
            return;
        }

        String staffId = staffList.get(selectedStaffPosition).getId();
        String scrapId = spinnerScrap.getSelectedItem().toString();

        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setUserId("LUTUZfLEHWb3xW6GUelLJnNWntE3");
        task.setAssigneeId(staffId);
        task.setScrapId(scrapId);
        task.setDescription("Collect scrap from user.");
        task.setStatus("assigned");
        task.setCreatedAt(new Timestamp(new Date()));
        task.setUpdatedAt(new Timestamp(new Date()));

        progressBar.setVisibility(View.VISIBLE);
        taskViewModel.createTask(task).observe(this, success -> {
            progressBar.setVisibility(View.GONE);
            if (success) {
                Toast.makeText(AssignTasksActivity.this, "Task assigned successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AssignTasksActivity.this, "Failed to assign task", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
