package com.example.notesmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.example.notesmvvm.Model.Notes_Entity;
import com.example.notesmvvm.R;
import com.example.notesmvvm.ViewModel.NotesViewModel;
import com.example.notesmvvm.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    NotesViewModel notesViewModel;
    ActivityInsertNotesBinding binding;

    String title, subtitle, notes;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.greenPriority.setImageResource(R.drawable.done);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);
                priority = "1";
            }
        });

        binding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(R.drawable.done);
                binding.redPriority.setImageResource(0);
                priority = "2";
            }
        });


        binding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(R.drawable.done);
                priority = "3";
            }
        });

        binding.doneNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = binding.notesTitle.getText().toString();
                subtitle = binding.notesSubtitle.getText().toString();
                notes = binding.notesData.getText().toString();

                CreateNotes(title, subtitle, notes);

            }

            private void CreateNotes(String title, String subtitle, String notes) {

                Date date = new Date();
                CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());


                Notes_Entity notes1 = new Notes_Entity();
                notes1.notesTitle = title;
                notes1.notesSubtitle = subtitle;
                notes1.notes = notes;
                notes1.notesDate = sequence.toString();
                notes1.notesPriority = priority;
                notesViewModel.insertNote(notes1);

                Toast.makeText(InsertNotesActivity.this, "Notes created successfully", Toast.LENGTH_SHORT).show();

                finish();
            }

        });


    }
}