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
        holder.bind(scrap);
    }

    @Override
    public int getItemCount() {
        return scrapList.size();
    }

    public void setScrapList(List<Scrap> scraps) {
        this.scrapList = scraps;
        notifyDataSetChanged();
    }

    static class ScrapViewHolder extends RecyclerView.ViewHolder {

        private ImageView scrapImage;
        private TextView scrapName;
        private TextView scrapDescription;

        public ScrapViewHolder(@NonNull View itemView) {
            super(itemView);
            scrapImage = itemView.findViewById(R.id.scrapImage);
            scrapName = itemView.findViewById(R.id.scrapName);
            scrapDescription = itemView.findViewById(R.id.scrapDescription);
        }

        public void bind(Scrap scrap) {
            scrapName.setText(scrap.getName());
            scrapDescription.setText(scrap.getDescription());
            Glide.with(scrapImage.getContext()).load(scrap.getImageUrl()).into(scrapImage);
        }
    }
}
