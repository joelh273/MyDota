package com.example.mydota.UI.Views.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydota.Data.Model.Note;
import com.example.mydota.R;
import com.example.mydota.UI.Adapters.NotesAdapter;
import com.example.mydota.UI.ViewModels.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HeroNotesActivity extends AppCompatActivity implements NotesAdapter.OnListItemClickListener{

    private NotesViewModel viewModel;
    private int heroId;
    private EditText description;
    private RecyclerView notesRecyclerView;
    private FloatingActionButton addNewItem;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        viewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        notesRecyclerView = findViewById(R.id.notes);
        notesAdapter = new NotesAdapter(this);
        notesRecyclerView.setAdapter(notesAdapter);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        heroId = getIntent().getIntExtra("hero_id",0);
        viewModel.getHeroNotesDB(heroId);
        addNewItem = findViewById(R.id.notes_fab);
        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
        viewModel.getHeroNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesAdapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(notesAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(notesRecyclerView);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onListItemClick(final Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.add_note, null);
        description = view.findViewById(R.id.note_description_dialog);
        builder.setView(view);

        builder.setPositiveButton("Edit Note", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int i = note.getId();
                String txt = description.getText().toString();
                System.out.println("edited");
                Note newNote = new Note(heroId, txt);
                newNote.setId(i);
                viewModel.update(newNote);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog ad = builder.create();
        ad.show();
    }

    private void saveNote() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.add_note, null);
        description = view.findViewById(R.id.note_description_dialog);
        builder.setView(view);

        builder.setPositiveButton("Add New Note", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txt = description.getText().toString();
                System.out.println("added");
                viewModel.insert(new Note(heroId, txt));

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog ad = builder.create();
        ad.show();
    }
}
