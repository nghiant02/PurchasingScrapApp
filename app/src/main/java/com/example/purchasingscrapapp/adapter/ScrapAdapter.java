package com.example.purchasingscrapapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.model.Scrap;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ScrapAdapter extends RecyclerView.Adapter<ScrapAdapter.ScrapViewHolder> {

    private List<Scrap> scrapList;

    public ScrapAdapter(List<Scrap> scrapList) {
        this.scrapList = scrapList;
    }

    @NonNull
    @Override
    public ScrapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scrap, parent, false);
        return new ScrapViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScrapViewHolder holder, int position) {
        Scrap scrap = scrapList.get(position);
        holder.textViewName.setText(scrap.getName());
        holder.textViewDescription.setText(scrap.getDescription());
        holder.textViewQuantity.setText(scrap.getQuantity() + " " + scrap.getUnit());
        holder.textViewLocation.setText(scrap.getLocation());
        Picasso.get().load(scrap.getImageUrl()).into(holder.imageViewScrap);
    }

    @Override
    public int getItemCount() {
        return scrapList != null ? scrapList.size() : 0;
    }

    public void setScrapList(List<Scrap> scrapList) {
        this.scrapList = scrapList;
        notifyDataSetChanged();
    }

    public static class ScrapViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName, textViewDescription, textViewQuantity, textViewLocation;
        public ImageView imageViewScrap;

        public ScrapViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            imageViewScrap = itemView.findViewById(R.id.imageViewScrap);
        }
    }
}
