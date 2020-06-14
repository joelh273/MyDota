package com.example.mydota.UI.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mydota.Data.Model.Hero;
import com.example.mydota.Data.Repository.DotaHeroesRepository;

import java.util.ArrayList;

public class DotaHeroesViewModel extends AndroidViewModel {

    private DotaHeroesRepository repository;

    public DotaHeroesViewModel(@NonNull Application application) {
        super(application);
        repository = DotaHeroesRepository.getInstance();
    }

    public LiveData<ArrayList<Hero>> getHeroes() {
        return repository.getHeroes();
    }

    public void getHeroesAPI(String string)
    {
        repository.getHeroesAPI(string);
    }
}
