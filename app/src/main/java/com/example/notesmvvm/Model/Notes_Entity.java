package com.example.notesmvvm.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// This is a Entity class.

// Declare table name outside the class.
@Entity(tableName = "Notes_Database")
public class Notes_Entity {

    // Here Declare primary.
    @PrimaryKey(autoGenerate = true)
    public int id;

    // here Declare all variables.
    @ColumnInfo(name = "notes_title")
    public String notesTitle;

    @ColumnInfo(name = "notes_subtitle")
    public String notesSubtitle;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "notes_date")
    public String notesDate;

    @ColumnInfo(name = "notes_priority")
    public String notesPriority;

}
