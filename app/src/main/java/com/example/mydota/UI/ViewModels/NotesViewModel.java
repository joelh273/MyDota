package com.example.mydota.UI.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mydota.Data.Model.Note;
import com.example.mydota.Data.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private NotesRepository repository;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        repository = NotesRepository.getInstance(application);
    }

    public LiveData<List<Note>> getHeroNotes() {
        return repository.getAllNotes();
    }

    public void getHeroNotesDB(int heroId) { repository.getHeroNotesDB(heroId); }


    public void insert(final Note note) {
        repository.insert(note);
    }

    public void update(final Note note) {
        repository.update(note);
    }

    public void delete(final Note note) {
        repository.delete(note);
    }
}
