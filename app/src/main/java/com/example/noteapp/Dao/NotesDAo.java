package com.example.noteapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteapp.Model.Note;

import java.util.List;

@androidx.room.Dao
public interface NotesDAo {
    @Query("SELECT * FROM Note_Database")
    LiveData<List<Note>> getallNotes();

    @Insert
    void  insertNotes(Note... notes);

    @Query("DELETE FROM Note_Database WHERE id =:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Note note);
}
