package com.example.mydota.Data.API;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DotaAPI {

    @GET("/api/heroes")
    Call<ArrayList<HeroesResponse>> getAllHeroes();
    @GET("/api/teams")
    Call<ArrayList<TeamsResponse>> getTeams();
    @GET("/api/proPlayers")
    Call<ArrayList<PlayersResponse>> getPlayers();
}
