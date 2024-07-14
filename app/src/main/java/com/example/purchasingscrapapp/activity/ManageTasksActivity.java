package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.adapter.TaskAdapter;
import com.example.purchasingscrapapp.model.Task;
import com.example.purchasingscrapapp.viewmodel.TaskViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ManageTasksActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private TaskAdapter taskAdapter;
    private RecyclerView recyclerViewTasks;
    private ProgressBar progressBar;
    private List<Task> taskList = new ArrayList<>();
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_tasks);

        initViews();

        taskAdapter = new TaskAdapter(task -> {
            // Handle task item click if needed
        }, (task, newStatus) -> {
            task.setStatus(newStatus);
            taskViewModel.updateTask(task).observe(this, success -> {
                if (success) {
                    Toast.makeText(ManageTasksActivity.this, "Task updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ManageTasksActivity.this, "Failed to update task", Toast.LENGTH_SHORT).show();
                }
            });
        });

        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setHasFixedSize(true);
        recyclerViewTasks.setAdapter(taskAdapter);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        loadTasks();
    }

    private void initViews() {
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        progressBar = findViewById(R.id.progressBar);
    }

    private void loadTasks() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewTasks.setVisibility(View.GONE);

        taskViewModel.getTasksByAssignee(currentUserId).observe(this, tasks -> {
            taskList = tasks;
            taskAdapter.setTasks(tasks);
            progressBar.setVisibility(View.GONE);
            recyclerViewTasks.setVisibility(View.VISIBLE);
        });
    }
}
