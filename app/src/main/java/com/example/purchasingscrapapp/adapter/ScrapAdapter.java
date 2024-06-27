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

import java.util.List;

public class ScrapAdapter extends RecyclerView.Adapter<ScrapAdapter.ScrapViewHolder> {

    private List<Scrap> scrapList;

    public ScrapAdapter(List<Scrap> scrapList) {
        this.scrapList = scrapList;
    }

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
        holder.priceTextView.setText(String.valueOf(scrap.getReferencePrice()));
        holder.quantityTextView.setText(String.valueOf(scrap.getQuantity()));
        holder.unitTextView.setText(scrap.getUnit());
        holder.locationTextView.setText(scrap.getLocation());
        Glide.with(holder.itemView.getContext()).load(scrap.getImageUrl()).into(holder.scrapImageView);
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
        TextView nameTextView, descriptionTextView, priceTextView, quantityTextView, unitTextView, locationTextView;
        ImageView scrapImageView;

        public ScrapViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            unitTextView = itemView.findViewById(R.id.unitTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            scrapImageView = itemView.findViewById(R.id.scrapImageView);
        }
    }
}
