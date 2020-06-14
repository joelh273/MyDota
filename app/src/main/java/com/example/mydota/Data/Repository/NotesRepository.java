package com.example.mydota.Data.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mydota.Data.Database.NotesDao;
import com.example.mydota.Data.Database.NotesDatabase;
import com.example.mydota.Data.Model.Note;

import java.util.List;

public class NotesRepository {

    private NotesDao notesDao;
    private static NotesRepository instance;
    private LiveData<List<Note>> allNotes;

    private NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getInstance(application);
        notesDao = database.noteDao();
    }
    public void getHeroNotesDB(int heroId)
    {
        allNotes = notesDao.getAllHeroNotes(heroId);
    }


    public static synchronized NotesRepository getInstance(Application application) {
        if (instance == null)
            instance = new NotesRepository(application);

        return instance;
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        new InsertNoteAsync(notesDao).execute(note);
    }


    private static class InsertNoteAsync extends AsyncTask<Note, Void, Void> {
        private NotesDao notesDao;

        private InsertNoteAsync(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDao.insert(notes[0]);
            return null;
        }
    }

    public void delete(Note note) {
        new DeleteNotesAsyncTask(notesDao).execute(note);
    }

    private static class DeleteNotesAsyncTask extends AsyncTask<Note, Void, Void> {
        private NotesDao notesDao;

        private DeleteNotesAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDao.delete(notes[0]);
            return null;
        }
    }

    public void update(Note note) {
        new UpdateNotesAsyncTask(notesDao).execute(note);
    }

    private static class UpdateNotesAsyncTask extends AsyncTask<Note, Void, Void> {
        private NotesDao notesDao;

        private UpdateNotesAsyncTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notesDao.update(notes[0]);
            return null;
        }
    }
}
