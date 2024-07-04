package com.example.purchasingscrapapp.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.adapter.ScrapAdapter;
import com.example.purchasingscrapapp.viewmodel.ScrapViewModel;

public class ScrapListFragment extends Fragment {

    private ScrapViewModel scrapViewModel;
    private ScrapAdapter scrapAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scrap_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewScrap);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        scrapAdapter = new ScrapAdapter();
        recyclerView.setAdapter(scrapAdapter);

        scrapViewModel = new ViewModelProvider(this).get(ScrapViewModel.class);
        scrapViewModel.getScrapList().observe(getViewLifecycleOwner(), scraps -> {
            scrapAdapter.setScrapList(scraps);
        });

        scrapViewModel.fetchScraps();
    }
}
