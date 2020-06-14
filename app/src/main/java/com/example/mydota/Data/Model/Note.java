package com.example.mydota.Data.Model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int heroId;
    private String description;


    public Note(int heroId, String description) {
        this.heroId = heroId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeroId() { return heroId; }

    public void setHeroID(int heroId) {
        this.heroId = heroId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
