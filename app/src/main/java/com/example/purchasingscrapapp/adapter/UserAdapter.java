package com.example.purchasingscrapapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> users = new ArrayList<>();

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User currentUser = users.get(position);
        holder.textViewName.setText(currentUser.getName());
        holder.textViewEmail.setText(currentUser.getEmail());
        holder.textViewRole.setText(currentUser.getRole());
        holder.textViewStatus.setText(currentUser.getStatus());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewEmail;
        private TextView textViewRole;
        private TextView textViewStatus;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewEmail = itemView.findViewById(R.id.text_view_email);
            textViewRole = itemView.findViewById(R.id.text_view_role);
            textViewStatus = itemView.findViewById(R.id.text_view_status);
        }
    }
}
