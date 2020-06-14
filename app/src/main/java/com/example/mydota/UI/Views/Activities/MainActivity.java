package com.example.mydota.UI.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mydota.R;
import com.example.mydota.UI.ViewModels.DotaHeroesViewModel;
import com.example.mydota.UI.ViewModels.ProDotaViewModel;

public class MainActivity extends AppCompatActivity {
    DotaHeroesViewModel heroesViewModel;
    ProDotaViewModel proDotaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heroesViewModel = new ViewModelProvider(this).get(DotaHeroesViewModel.class);
        proDotaViewModel = new ViewModelProvider(this).get(ProDotaViewModel.class);
        initDataFromAPI();
    }

    private void initDataFromAPI() {
        heroesViewModel.getHeroesAPI("all");
        proDotaViewModel.getTeamsAPI();
        proDotaViewModel.getPlayersAPI();
    }

    public void onClickHeroes(View v)
    {
        Intent intent = new Intent(this, HeroesActivity.class);
        startActivity(intent);
    }
    public void onClickProTeams(View v)
    {
        Intent intent = new Intent(this, ProDotaActivity.class);
        startActivity(intent);
    }
}
