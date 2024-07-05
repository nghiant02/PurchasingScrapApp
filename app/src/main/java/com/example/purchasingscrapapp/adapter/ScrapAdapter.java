package com.example.purchasingscrapapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.Scrap;

import java.util.ArrayList;
import java.util.List;

public class ScrapAdapter extends RecyclerView.Adapter<ScrapAdapter.ScrapViewHolder> {

    private List<Scrap> scrapList = new ArrayList<>();

    @NonNull
    @Override
    public ScrapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scrap, parent, false);
        return new ScrapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScrapViewHolder holder, int position) {
        Scrap scrap = scrapList.get(position);
        holder.nameTextView.setText(scrap.getName());
        holder.descriptionTextView.setText(scrap.getDescription());
        Glide.with(holder.imageView.getContext()).load(scrap.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return scrapList.size();
    }

    public void setScrapList(List<Scrap> scrapList) {
        this.scrapList = scrapList;
        notifyDataSetChanged();
    }

    static class ScrapViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        ImageView imageView;

        ScrapViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_scrap_name);
            descriptionTextView = itemView.findViewById(R.id.text_view_scrap_description);
            imageView = itemView.findViewById(R.id.image_view_scrap);
        }
    }
}
