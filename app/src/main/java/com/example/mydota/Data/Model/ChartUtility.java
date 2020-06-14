package com.example.mydota.Data.Model;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartUtility {
    ArrayList<BarEntry> teamsValues;
    ArrayList<BarEntry> playersValues;
    BarDataSet teamsSet;
    BarDataSet playersSet;
    List<Team> teams;
    List<Player> players;

    public void setupPlayersChart(BarChart chart) {
        chart.getAxisRight().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        playersValues = new ArrayList<>();
        playersSet = new BarDataSet(playersValues, " Pro Players");
        XAxis xLabels = chart.getXAxis();
        xLabels.setDrawGridLines(false);
        xLabels.setLabelCount(10);
        xLabels.setGranularity(1f);
        xLabels.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return players.get((int) value).getCountryCode();
            }
        });
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

    }
    public void setupTeamsChart(BarChart chart) {
        chart.getAxisRight().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        teamsValues = new ArrayList<>();
        XAxis xLabels = chart.getXAxis();
        xLabels.setDrawGridLines(false);
        xLabels.setGranularity(1f);
        xLabels.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return teams.get((int) value).getName();
            }
        });
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

    }

    public void handlePlayersGraph(BarChart chart, List<Player> players) {
        this.players = players;
        playersValues.clear();
        for (int i = 0; i < players.size(); i++) {
            playersValues.add(new BarEntry(i,players.get(i).getCount()));
        }
        playersSet.setValues(playersValues);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(playersSet);
        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setBarWidth(0.9f);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.setFitBars(true);
        chart.notifyDataSetChanged();
        chart.invalidate();
    }
    public void handleTeamsGraph(BarChart chart, List<Team> teams) {
        this.teams = teams;
        teamsValues.clear();
        for (int i = 0; i < teams.size(); i++) {
            teamsValues.add(new BarEntry(i,new float[]{teams.get(i).getWins(), teams.get(i).getLosses()}));
        }
        teamsSet = new BarDataSet(teamsValues, " Win Rate");
        teamsSet.setDrawIcons(false);
        teamsSet.setColors(Color.GREEN,Color.RED);
        teamsSet.setStackLabels(new String[]{"Wins", "Losses"});
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(teamsSet);
        BarData data = new BarData(dataSets);
        data.setValueFormatter(new StackedValueFormatter(false, "", 1));
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);
        chart.setFitBars(true);
        chart.notifyDataSetChanged();
        chart.invalidate();
    }
}
