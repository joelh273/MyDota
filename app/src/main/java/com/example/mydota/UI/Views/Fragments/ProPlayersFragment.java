package com.example.mydota.UI.Views.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.mydota.Data.Model.ChartUtility;
import com.example.mydota.Data.Model.Player;
import com.example.mydota.R;
import com.example.mydota.UI.ViewModels.ProDotaViewModel;
import com.github.mikephil.charting.charts.BarChart;

import java.util.List;

public class ProPlayersFragment extends Fragment {

    private List<Player> players;
    private BarChart chart;
    private ChartUtility chartUtility;

    public ProPlayersFragment( ProDotaViewModel viewModel, ChartUtility cUtility) {

        this.chartUtility = cUtility;

        viewModel.getPlayers().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> data) {
                players = data;
                chartUtility.handlePlayersGraph(chart, players);
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pro_dota, container, false);
        chart = (BarChart) rootView.findViewById(R.id.proDotaChart);
        chartUtility.setupPlayersChart(chart);
        return rootView;
    }
}
