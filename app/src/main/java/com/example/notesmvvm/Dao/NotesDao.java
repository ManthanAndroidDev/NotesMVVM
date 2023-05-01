package com.example.notesmvvm.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesmvvm.Model.Notes_Entity;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_Database")
        // using above query we get all the data from table (Notes_Database) amd store in the getsAllNotes.
    LiveData<List<Notes_Entity>> getsAllNotes();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<Notes_Entity>> highToLow();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority ASC")
    LiveData<List<Notes_Entity>> lowToHigh();

    // Now we will create query for insert.
    @Insert
    void insertNote(Notes_Entity... notes);

    //Query for Delete
    @Query("DELETE FROM Notes_Database WHERE id =:id")
    void deleteNote(int id);

    //Query for update
    @Update
    void updateNote(Notes_Entity notes);

}
