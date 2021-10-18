package com.example.noteapp.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Note_Database")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_Title")
    public String notesTitle;

    @ColumnInfo(name = "notes_Subtitle")
    public String notesSubtitle;

    @ColumnInfo(name = "notes_Date")
    public String notesDate;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "notes_Priority")
    public String notesPriority;
}
