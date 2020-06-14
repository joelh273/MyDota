package com.example.mydota.UI.Views.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mydota.Data.Model.ChartUtility;
import com.example.mydota.R;
import com.example.mydota.UI.ViewModels.ProDotaViewModel;
import com.example.mydota.UI.Views.Fragments.ProPlayersFragment;
import com.example.mydota.UI.Views.Fragments.ProTeamsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProDotaActivity extends AppCompatActivity {

    private ProDotaViewModel viewModel;
    ChartUtility chartUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_teams);
        viewModel = new ViewModelProvider(this).get(ProDotaViewModel.class);
        chartUtility = new ChartUtility();
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new ProTeamsFragment( viewModel,chartUtility)).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_teams:
                            fragment = new ProTeamsFragment( viewModel,chartUtility);
                            break;
                        case R.id.nav_players:
                            fragment = new ProPlayersFragment( viewModel,chartUtility);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fragment).commit();
                    return true;
                }
            };
}
