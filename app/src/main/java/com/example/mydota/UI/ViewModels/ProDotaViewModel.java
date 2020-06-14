package com.example.mydota.UI.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mydota.Data.Model.Player;
import com.example.mydota.Data.Model.Team;
import com.example.mydota.Data.Repository.ProDotaRepository;

import java.util.ArrayList;

public class ProDotaViewModel extends AndroidViewModel {

    private ProDotaRepository repository;

    public ProDotaViewModel(@NonNull Application application) {
        super(application);
        repository = ProDotaRepository.getInstance();
    }
    public LiveData<ArrayList<Team>> getTeams() {
        return repository.getTeams();
    }

    public void getTeamsAPI()
    {
        repository.getProTeamsAPI();
    }

    public LiveData<ArrayList<Player>> getPlayers() {
        return repository.getPlayers();
    }

    public void getPlayersAPI()
    {
        repository.getProPlayersAPI();
    }
}
