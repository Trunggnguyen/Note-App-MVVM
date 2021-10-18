package com.example.noteapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.noteapp.Model.Note;
import com.example.noteapp.Repository.NoteRepository;


import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public  NoteRepository repository;
    public LiveData<List<Note>> getAllNotes;

    public NoteViewModel( Application application) {
        super(application);

         repository = new NoteRepository(application);
         getAllNotes = repository.getallNotes;

    }


    public void insertNote(Note note){
        repository.insertNote(note);
    }

    public void deleteNote(int id){
        repository.deleteNote(id);
    }

    public void updatetNote(Note note){
        repository.updatetNote(note);
    }
}
