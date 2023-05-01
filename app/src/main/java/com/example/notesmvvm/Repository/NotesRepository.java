package com.example.notesmvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesmvvm.Dao.NotesDao;
import com.example.notesmvvm.Database.NotesDatabase;
import com.example.notesmvvm.Model.Notes_Entity;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes_Entity>> getAllNotes;

    public LiveData<List<Notes_Entity>> highToLow;

    public LiveData<List<Notes_Entity>> lowToHigh;


    // Now we want to get dao from NotesDatabase, for this we will create a below method.

    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        // now we get notesDao here. created in the NotesDatabase.
        notesDao = database.notesDao();
        getAllNotes = notesDao.getsAllNotes();
        highToLow = notesDao.highToLow();
        lowToHigh = notesDao.lowToHigh();
    }

    public void insertNotes(Notes_Entity notes) {
        notesDao.insertNote(notes);
    }

    public void DeleteNotes(int id) {
        notesDao.deleteNote(id);
    }

    public void UpdateNotes(Notes_Entity notes) {
        notesDao.updateNote(notes);
    }
}
