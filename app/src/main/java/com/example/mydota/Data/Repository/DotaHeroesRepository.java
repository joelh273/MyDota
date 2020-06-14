package com.example.mydota.Data.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mydota.Data.API.DotaAPI;
import com.example.mydota.Data.API.HeroesResponse;
import com.example.mydota.Data.API.ServiceGenerator;
import com.example.mydota.Data.Model.Hero;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DotaHeroesRepository {
    private MutableLiveData<ArrayList<Hero>> heroes = new MutableLiveData<>();
    private static DotaHeroesRepository instance;


    public static DotaHeroesRepository getInstance() {
        if (instance == null) {
            instance = new DotaHeroesRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<Hero>> getHeroes() {
        return heroes;
    }

    public void getHeroesAPI(final String string) {
        DotaAPI dotaAPI = ServiceGenerator.getDotaApi();
        Call<ArrayList<HeroesResponse>> call = dotaAPI.getAllHeroes();
        call.enqueue(new Callback<ArrayList<HeroesResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<HeroesResponse>> call, Response<ArrayList<HeroesResponse>>response) {
                if (response.code() == 200) {
                    ArrayList<Hero> heroList = new ArrayList<>();
                    if(string.equals("all"))
                    {
                        for (int i = 0; i < response.body().size(); i++) {
                                String name = response.body().get(i).getName().replace("npc_dota_hero_", "");
                                String imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + name + "_sb.png";
                                Hero local;
                                local = new Hero(response.body().get(i).getId(), name, response.body().get(i).getPrimary_attr(), imageUrl);
                                heroList.add(local);
                        }
                    }
                    else {
                        for (int i = 0; i < response.body().size(); i++) {
                            if (string.equals(response.body().get(i).getPrimary_attr())) {
                                String name = response.body().get(i).getName().replace("npc_dota_hero_", "");
                                String imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + name + "_sb.png";
                                Hero local;
                                local = new Hero(response.body().get(i).getId(), name, response.body().get(i).getPrimary_attr(), imageUrl);
                                heroList.add(local);
                            }
                        }
                    }
                    heroes.setValue(heroList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<HeroesResponse>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong with API heroes endpoint" + t.toString());
                ArrayList<Hero> heroList = new ArrayList<>();
                heroList.add(new Hero(1,"bounty hunter","agi","aaaa"));
                heroList.add(new Hero(2,"bounty mama","int","bbbb"));
                heroes.setValue(heroList);
            }
        });
    }

}
