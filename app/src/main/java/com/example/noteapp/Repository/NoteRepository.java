package com.example.noteapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.noteapp.Dao.NotesDAo;
import com.example.noteapp.Database.NotesDatabase;
import com.example.noteapp.Model.Note;

import java.util.List;

public class NoteRepository {


    public NotesDAo notesDAo;
    public LiveData<List<Note>> getallNotes;




    public  NoteRepository(Application application){
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDAo = database.notesDAo();
        getallNotes = notesDAo.getallNotes();

    }

    public void insertNote(Note note){
        notesDAo.insertNotes(note);
    }

    public void deleteNote(int id){
        notesDAo.deleteNotes(id);
    }

    public void updatetNote(Note note){
        notesDAo.updateNotes(note);
    }
}
