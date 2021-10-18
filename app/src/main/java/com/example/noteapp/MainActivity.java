package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.noteapp.Activity.InsertNoteActivity;
import com.example.noteapp.Adapter.Note_Adapter;
import com.example.noteapp.ViewModel.NoteViewModel;
import com.example.noteapp.databinding.ActivityInsertNoteBinding;
import com.example.noteapp.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    NoteViewModel noteViewModel;
    ActivityMainBinding binding;
    Note_Adapter note_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        binding.newnotesbt.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertNoteActivity.class));
        });


        noteViewModel.getAllNotes.observe(this, notes -> {
            binding.noterecyycleview.setLayoutManager(new GridLayoutManager(this, 2));
            note_adapter = new Note_Adapter(MainActivity.this, notes);
            binding.noterecyycleview.setAdapter(note_adapter);

        });
    }
}