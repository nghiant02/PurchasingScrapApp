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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scrap, parent, false);
        return new ScrapViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScrapViewHolder holder, int position) {
        Scrap scrap = scrapList.get(position);
        holder.textViewName.setText(scrap.getName());
        holder.textViewDescription.setText(scrap.getDescription());
        holder.textViewQuantity.setText(String.valueOf(scrap.getQuantity()));
        holder.textViewLocation.setText(scrap.getLocation());
        holder.textViewReferencePrice.setText(String.valueOf(scrap.getReferencePrice()));
        holder.textViewStatus.setText(scrap.getStatus());

        Glide.with(holder.itemView.getContext())
                .load(scrap.getImageUrl())
                .into(holder.imageViewScrap);
    }

    @Override
    public int getItemCount() {
        return scrapList.size();
    }

    public static class ScrapViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName, textViewDescription, textViewQuantity, textViewLocation, textViewReferencePrice, textViewStatus;
        public ImageView imageViewScrap;

        public ScrapViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewReferencePrice = itemView.findViewById(R.id.textViewReferencePrice);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            imageViewScrap = itemView.findViewById(R.id.imageViewScrap);
        }
    }
}
