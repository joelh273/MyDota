package com.example.mydota.Data.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mydota.Data.Model.Note;

import java.util.List;



@Dao
public interface NotesDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);


    @Query("SELECT * FROM notes_table WHERE heroId = :heroId")
    LiveData<List<Note>> getAllHeroNotes(int heroId);

}
