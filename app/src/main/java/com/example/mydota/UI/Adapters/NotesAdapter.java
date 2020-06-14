package com.example.mydota.UI.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydota.Data.Model.Note;
import com.example.mydota.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    public interface OnListItemClickListener {
        void onListItemClick(Note note);
    }

    private List<Note> notes;
    final private OnListItemClickListener mOnListItemClickListener;

    public NotesAdapter(OnListItemClickListener listener) {
        mOnListItemClickListener = listener;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        if (notes != null) {
            Note guidancePosition = notes.get(position);
            holder.notesDescription.setText(guidancePosition.getDescription());
        }
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (notes == null) {
            return 0;
        }
        return notes.size();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView notesDescription;


        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            notesDescription = itemView.findViewById(R.id.guidance_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            mOnListItemClickListener.onListItemClick(notes.get(getAdapterPosition()));
        }
    }
}