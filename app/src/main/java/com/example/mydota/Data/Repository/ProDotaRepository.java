package com.example.mydota.Data.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mydota.Data.API.DotaAPI;
import com.example.mydota.Data.API.PlayersResponse;
import com.example.mydota.Data.API.ServiceGenerator;
import com.example.mydota.Data.API.TeamsResponse;
import com.example.mydota.Data.Model.Player;
import com.example.mydota.Data.Model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProDotaRepository {
    private MutableLiveData<ArrayList<Team>> teams = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Player>> players = new MutableLiveData<>();
    private static ProDotaRepository instance;


    public static ProDotaRepository getInstance() {
        if (instance == null) {
            instance = new ProDotaRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<Team>> getTeams() {
        return teams;
    }

    public LiveData<ArrayList<Player>> getPlayers() {
        return players;
    }

    public void getProTeamsAPI() {
        DotaAPI dotaAPI = ServiceGenerator.getDotaApi();
        Call<ArrayList<TeamsResponse>> call = dotaAPI.getTeams();
        call.enqueue(new Callback<ArrayList<TeamsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TeamsResponse>> call, Response<ArrayList<TeamsResponse>>response) {
                if (response.code() == 200) {
                    ArrayList<Team> teamList = new ArrayList<>();
                    if (response.body().size() > 10) {
                        for (int i = 0; i < 10; i++) {
                            Team local;
                            local = new Team(response.body().get(i).getWins(), response.body().get(i).getLosses(), response.body().get(i).getName());
                            teamList.add(local);
                        }
                        teams.setValue(teamList);
                    }
                    else {
                        for (int i = 0; i < response.body().size(); i++) {
                            Team local;
                            local = new Team(response.body().get(i).getWins(), response.body().get(i).getLosses(), response.body().get(i).getName());
                            teamList.add(local);
                        }
                        teams.setValue(teamList);
                    }
                }
                }

            @Override
            public void onFailure(Call<ArrayList<TeamsResponse>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong with Pro teams API endpoint" + t.toString());
                ArrayList<Team> teamList = new ArrayList<>();
                teamList.add(new Team(100,50,"Virtus Pro"));
                teamList.add(new Team(300,400,"Natus Vincere"));
                teams.setValue(teamList);
            }
        });
    }

    public void getProPlayersAPI() {
        DotaAPI dotaAPI = ServiceGenerator.getDotaApi();
        Call<ArrayList<PlayersResponse>> call = dotaAPI.getPlayers();
        call.enqueue(new Callback<ArrayList<PlayersResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PlayersResponse>> call, Response<ArrayList<PlayersResponse>>response) {
                if (response.code() == 200) {
                    ArrayList<Player> playerList = new ArrayList<>();
                    playerList.add(new Player("MD"));
                        for (int i = 0; i < response.body().size(); i++)
                        {
                            Player local = new Player(response.body().get(i).getLoccountrycode()) ;
                            if(local.getCountryCode()==null)
                            {
                                local.unknownCountry();
                            }
                            boolean check = false;
                            for(int j=0;j<playerList.size();j++)
                            {
                                check = false;
                                if(local.getCountryCode().equals(playerList.get(j).getCountryCode()))
                                {
                                    playerList.get(j).increment();
                                    break;
                                }
                                check = true;

                            }
                            if(check == true) {
                                playerList.add(local);
                            }

                        }
                    Collections.sort(playerList, new Comparator<Player>() {
                        @Override public int compare(Player p1, Player p2) {
                            return p1.getCount()- p2.getCount();
                        }

                    });
                        players.setValue(playerList);
                        for(int i=0;i<playerList.size();i++)
                        {
                            Log.i("Retrofit", "Something went wrong with Pro players API endpointaaaaaaaaaaa" + playerList.get(i).getCount()+ playerList.get(i).getCountryCode());
                        }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PlayersResponse>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong with Pro players API endpoint" + t.toString());
                ArrayList<Player> playerList = new ArrayList<>();
                playerList.add(new Player("DE"));
                playerList.add(new Player("MD"));
                players.setValue(playerList);
            }
        });
    }

}
