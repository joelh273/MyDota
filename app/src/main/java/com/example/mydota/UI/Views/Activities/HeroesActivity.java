package com.example.mydota.UI.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydota.Data.Model.Hero;
import com.example.mydota.R;
import com.example.mydota.UI.Adapters.HeroListAdapter;
import com.example.mydota.UI.ViewModels.DotaHeroesViewModel;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

public class HeroesActivity extends AppCompatActivity implements HeroListAdapter.OnListItemClickedListener{
    private DotaHeroesViewModel viewModel;
    private HeroListAdapter adapter;
    private RecyclerView recyclerView;
    FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        setViewModel();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        recyclerView = findViewById(R.id.heroes);
        adapter = new HeroListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.str:
                viewModel.getHeroesAPI("str");
                break;
            case R.id.agi:
                viewModel.getHeroesAPI("agi");
                break;
            case R.id.intel:
                viewModel.getHeroesAPI("int");
                break;
            case R.id.home:
                viewModel.getHeroesAPI("all");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void setViewModel() {
        viewModel = new ViewModelProvider(this).get(DotaHeroesViewModel.class);

        viewModel.getHeroes().observe(this, new Observer<ArrayList<Hero>>() {

            @Override
            public void onChanged(ArrayList<Hero> heroes) {
                adapter.setHeroes(heroes);
            }
        });
    }

    @Override
    public void onListItemClicked(int heroId,String heroName) {
        Intent intent = new Intent(this, HeroNotesActivity.class);
        intent.putExtra("hero_id",heroId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Bundle bundle = new Bundle();
        bundle.putInt("hero_id",heroId);
        bundle.putString("hero_name",heroName);
        mFirebaseAnalytics.logEvent("Hero_Selected",bundle);
    }
}
