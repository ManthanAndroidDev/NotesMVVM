package com.example.notesmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.notesmvvm.Activity.InsertNotesActivity;
import com.example.notesmvvm.Adapter.NotesAdapter;
import com.example.notesmvvm.Model.Notes_Entity;
import com.example.notesmvvm.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;

    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;

    TextView nofilter, hightolow, lowtohigh;
    List<Notes_Entity> filterNotesalllist;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        newNotesBtn = findViewById(R.id.newNotesBtn);
        notesRecycler = findViewById(R.id.notesRecycler);

        nofilter = findViewById(R.id.nofilter);
        hightolow = findViewById(R.id.hightolow);
        lowtohigh = findViewById(R.id.lowtohigh);

        nofilter.setBackgroundResource(R.drawable.filter_selected_shape);

        nofilter.setOnClickListener(view -> {

            loadData(0);
            hightolow.setBackgroundResource(R.drawable.filter_un_shape);
            lowtohigh.setBackgroundResource((R.drawable.filter_un_shape));
            nofilter.setBackgroundResource(R.drawable.filter_selected_shape);
        });

        hightolow.setOnClickListener(view -> {

            loadData(1);
            hightolow.setBackgroundResource(R.drawable.filter_selected_shape);
            lowtohigh.setBackgroundResource((R.drawable.filter_un_shape));
            nofilter.setBackgroundResource((R.drawable.filter_un_shape));
        });

        lowtohigh.setOnClickListener(view -> {

            loadData(2);
            hightolow.setBackgroundResource((R.drawable.filter_un_shape));
            lowtohigh.setBackgroundResource(R.drawable.filter_selected_shape);
            nofilter.setBackgroundResource((R.drawable.filter_un_shape));
        });


        newNotesBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });

        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes_Entity>>() {
            @Override
            public void onChanged(List<Notes_Entity> notes_entities) {
                setAdapter(notes_entities);
                filterNotesalllist = notes_entities;
            }
        });


    }

    private void loadData(int i) {

        if (i == 0) {

            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes_Entity>>() {
                @Override
                public void onChanged(List<Notes_Entity> notes_entities) {
                    setAdapter(notes_entities);
                    filterNotesalllist = notes_entities;
                }
            });

        } else if (i == 1) {

            notesViewModel.highToLow.observe(this, new Observer<List<Notes_Entity>>() {
                @Override
                public void onChanged(List<Notes_Entity> notes_entities) {
                    setAdapter(notes_entities);
                    filterNotesalllist = notes_entities;
                }
            });

        } else if (i == 2) {

            notesViewModel.lowToHigh.observe(this, new Observer<List<Notes_Entity>>() {
                @Override
                public void onChanged(List<Notes_Entity> notes_entities) {
                    setAdapter(notes_entities);
                    filterNotesalllist = notes_entities;
                }
            });
        }
    }

    public void setAdapter(List<Notes_Entity> notes_entities) {


        notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this, notes_entities);
        notesRecycler.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_notes, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search notes here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                NotesFilter(s);
                return false;
            }


        });

        return super.onCreateOptionsMenu(menu);
    }
    private void NotesFilter(String s) {

        ArrayList<Notes_Entity> FilterNames = new ArrayList<>();

        for (Notes_Entity notes_entity: this.filterNotesalllist){

            if(notes_entity.notesTitle.contains(s) || notes_entity.notesSubtitle.contains(s)){
                FilterNames.add(notes_entity);
            }
        }
        this.adapter.searchNotes(FilterNames);
    }
}