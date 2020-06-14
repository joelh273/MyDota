package com.example.mydota.Data.Model;

public class Player {

    private String countryCode;
    private int count;

    public Player(String countryCode)
    {
        this.countryCode = countryCode;
        this.count = 1;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getCount() {
        return count;
    }
    public void increment()
    {
        count++;
    }
    public void unknownCountry()
    {
        countryCode ="Unknown";
    }

}
