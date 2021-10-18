package com.example.noteapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.noteapp.Model.Note;
import com.example.noteapp.Dao.NotesDAo;

@Database(entities =  {Note.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDAo notesDAo();

    public static NotesDatabase INSTANCE;

    public static NotesDatabase getDatabaseInstance(Context context) {
        if(INSTANCE == null ){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class, "Note_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }




}
