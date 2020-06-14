package com.example.mydota.Data.Model;

public class Team {
    private int wins;
    private int losses;
    private String name;

    public Team(int wins,int losses,String name)
    {
        this.wins = wins;
        this.losses = losses;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }
}
