package com.example.noteapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.noteapp.Model.Note;
import com.example.noteapp.R;
import com.example.noteapp.ViewModel.NoteViewModel;
import com.example.noteapp.databinding.ActivityUpdateNoteBinding;
import com.example.noteapp.databinding.ActivityViewNoteBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class UpdateNoteActivity extends AppCompatActivity {

    ActivityUpdateNoteBinding binding;
    Intent intent;
    int id;
    String notetitle;
    String notesubtitle;
    String notedetail;
    String notepriority;
    String notedate;
    String priority =  "3";
    NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();

        id = intent.getIntExtra("id", 1);
        notetitle = intent.getStringExtra("notetitle");
        notesubtitle = intent.getStringExtra("notesubtitle");
        notepriority = intent.getStringExtra("notepriority");
        notedetail = intent.getStringExtra("notedetail");
        notedate = intent.getStringExtra("notedate");

        binding.updatnotetitle.setText(notetitle);
        binding.updatnotesubtitle.setText(notesubtitle);
        binding.updatenote.setText(notedetail);


        if (notepriority.equals("3")){
            binding.redpriority.setImageResource(R.drawable.ic_baseline_check_24);
        }if (notepriority.equals("2")){
            binding.greenpriority.setImageResource(R.drawable.ic_baseline_check_24);
        }if (notepriority.equals("1")){
            binding.yellowpriority.setImageResource(R.drawable.ic_baseline_check_24);
        }

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        binding.redpriority.setOnClickListener(v -> {
            priority="1";
            binding.redpriority.setImageResource(R.drawable.ic_baseline_check_24);
            binding.yellowpriority.setImageResource(0);
            binding.greenpriority.setImageResource(0);
        });
        binding.yellowpriority.setOnClickListener(v -> {
            priority="1";
            binding.yellowpriority.setImageResource(R.drawable.ic_baseline_check_24);
            binding.redpriority.setImageResource(0);
            binding.greenpriority.setImageResource(0);
        });
        binding.greenpriority.setOnClickListener(v -> {
            priority="2";
            binding.greenpriority.setImageResource(R.drawable.ic_baseline_check_24);
            binding.redpriority.setImageResource(0);
            binding.yellowpriority.setImageResource(0);
        });
        binding.redpriority.setOnClickListener(v -> {
            priority="3";
            binding.redpriority.setImageResource(R.drawable.ic_baseline_check_24);
            binding.yellowpriority.setImageResource(0);
            binding.greenpriority.setImageResource(0);
        });

        binding.updatenotesbt.setOnClickListener(v -> {

           String title= binding.updatnotetitle.getText().toString();
           String subtitle = binding.updatnotesubtitle.getText().toString();
           String notes = binding.updatenote.getText().toString();

                updatenote( title, subtitle, notes);

        });



    }

    private void updatenote(String title, String subtitle, String notes) {

        DateFormat dateFormat= DateFormat.getDateInstance();
        Calendar cal = Calendar.getInstance();
        String curr_date = dateFormat.format(cal.getTime());

        Note note = new Note();
        note.id = id;
        note.notesTitle=title;
        note.notesSubtitle= subtitle;
        note.notes= notes;
        note.notesDate= curr_date;
        note.notesPriority= priority;

        noteViewModel.updatetNote(note);
        Toast.makeText(this, "Update notes succsessfully !", Toast.LENGTH_SHORT).show();

        Intent  databrestf = new Intent();

        databrestf.putExtra("id", id);
        databrestf.putExtra("notetitle", title);
        databrestf.putExtra("notesubtitle", subtitle);
        databrestf.putExtra("notepriority", priority);
        databrestf.putExtra("notedetail", notes);
        databrestf.putExtra("notedate", curr_date);

        setResult(RESULT_OK, databrestf);


        finish();
    }
}