package com.example.purchasingscrapapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Task> tasks = new ArrayList<>();
    private OnTaskClickListener listener;
    private OnTaskStatusChangeListener statusChangeListener;

    public TaskAdapter(OnTaskClickListener listener, OnTaskStatusChangeListener statusChangeListener) {
        this.listener = listener;
        this.statusChangeListener = statusChangeListener;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.textViewTaskDescription.setText(currentTask.getDescription());
        holder.textViewTaskStatus.setText(currentTask.getStatus());

        holder.buttonMarkComplete.setOnClickListener(v -> {
            if (statusChangeListener != null) {
                statusChangeListener.onStatusChange(currentTask, "completed");
            }
        });

        holder.buttonMarkInProgress.setOnClickListener(v -> {
            if (statusChangeListener != null) {
                statusChangeListener.onStatusChange(currentTask, "in_progress");
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        private TextView textViewTaskDescription;
        private TextView textViewTaskStatus;
        private Button buttonMarkComplete;
        private Button buttonMarkInProgress;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskDescription = itemView.findViewById(R.id.text_view_task_description);
            textViewTaskStatus = itemView.findViewById(R.id.text_view_task_status);
            buttonMarkComplete = itemView.findViewById(R.id.button_mark_complete);
            buttonMarkInProgress = itemView.findViewById(R.id.button_mark_in_progress);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onTaskClick(tasks.get(position));
                }
            });
        }
    }

    public interface OnTaskClickListener {
        void onTaskClick(Task task);
    }

    public interface OnTaskStatusChangeListener {
        void onStatusChange(Task task, String newStatus);
    }
}
