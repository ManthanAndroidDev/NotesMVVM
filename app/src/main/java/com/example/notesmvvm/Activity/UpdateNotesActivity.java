package com.example.notesmvvm.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesmvvm.Model.Notes_Entity;
import com.example.notesmvvm.R;
import com.example.notesmvvm.ViewModel.NotesViewModel;
import com.example.notesmvvm.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    NotesViewModel notesViewModel;

    String priority = "1";
    String stitle, ssubtitle, snotes, spriority;
    int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iid = getIntent().getIntExtra("id", 0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        snotes = getIntent().getStringExtra("note");
        spriority = getIntent().getStringExtra("priority");

        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upNotes.setText(snotes);

        if (spriority.equals("1")) {
            binding.greenPriority.setImageResource(R.drawable.done);
        } else if (spriority.equals("2")) {
            binding.yellowPriority.setImageResource(R.drawable.done);
        } else if (spriority.equals("3")) {
            binding.redPriority.setImageResource(R.drawable.done);
        }

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

        binding.UpdateNotesBtn.setOnClickListener(view -> {

            String title = binding.upTitle.getText().toString();
            String subtitle = binding.upSubtitle.getText().toString();
            String notes = binding.upNotes.getText().toString();

            UpdateNotes(title, subtitle, notes);

        });


    }

    private void UpdateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

        Notes_Entity updateNotes = new Notes_Entity();
        updateNotes.id = iid;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesDate = sequence.toString();
        updateNotes.notesPriority = priority;

        notesViewModel.updateNote(updateNotes);


        Toast.makeText(UpdateNotesActivity.this, "Notes updated successfully", Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.ic_delete) {

            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this, R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdateNotesActivity.this).inflate(R.layout.delete_bottom_sheet, (LinearLayout) findViewById(R.id.bottomSheet));

            sheetDialog.setContentView(view);

            TextView yes, no;

            yes = view.findViewById(R.id.delete_yes);
            no = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(view1 -> {

                notesViewModel.deleteNote(iid);
                finish();

            });

            no.setOnClickListener(view1 -> {

                sheetDialog.dismiss();

            });

            sheetDialog.show();

        }

        return true;
    }
}