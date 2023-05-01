package com.example.notesmvvm.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesmvvm.Activity.UpdateNotesActivity;
import com.example.notesmvvm.MainActivity;
import com.example.notesmvvm.Model.Notes_Entity;
import com.example.notesmvvm.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {

    MainActivity mainActivity;
    List<Notes_Entity> notes_entities;
    List<Notes_Entity> allNotesitem;


    public NotesAdapter(MainActivity mainActivity, List<Notes_Entity> notes_entities) {

        this.mainActivity = mainActivity;
        this.notes_entities = notes_entities;
        allNotesitem = new ArrayList<>(notes_entities);
    }

    public void searchNotes(List<Notes_Entity> filteredName){

        this.notes_entities = filteredName;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public notesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewholder holder, int position) {

        Notes_Entity note = notes_entities.get(position);

        if(note.notesPriority.equals("1")){
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        }else if(note.notesPriority.equals("2")){
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shpae);
        }else if(note.notesPriority.equals("3")){
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.notesDate.setText(note.notesDate);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);

            intent.putExtra("id", note.id);
            intent.putExtra("title", note.notesTitle);
            intent.putExtra("subtitle", note.notesSubtitle);
            intent.putExtra("note", note.notes);
            intent.putExtra("priority", note.notesPriority);

            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes_entities.size();
    }

    class notesViewholder extends RecyclerView.ViewHolder {

        TextView title, subtitle, notesDate;
        View notesPriority;

        public notesViewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);
        }
    }
}
