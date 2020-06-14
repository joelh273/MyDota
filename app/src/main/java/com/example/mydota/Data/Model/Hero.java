package com.example.mydota.Data.Model;

public class Hero {
    private int id;
    private String name;
    private String primary_attr;
    private String imageURL;

    public Hero(int id,String name,String primary_attr,String imageURL)
    {
        this.id = id;
        this.name = name;
        this.primary_attr = primary_attr;
        this.imageURL = imageURL;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getPrimary_attr() {
        return primary_attr;
    }


    public String getImageURL()
    {
        return  imageURL;
    }
}
