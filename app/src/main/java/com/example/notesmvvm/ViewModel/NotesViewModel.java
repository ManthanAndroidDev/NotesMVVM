package com.example.notesmvvm.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesmvvm.Model.Notes_Entity;
import com.example.notesmvvm.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    NotesRepository repository;
    public LiveData<List<Notes_Entity>> getAllNotes;

    public LiveData<List<Notes_Entity>> highToLow;

    public LiveData<List<Notes_Entity>> lowToHigh;

    public NotesViewModel(Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getAllNotes;
        highToLow = repository.highToLow;
        lowToHigh = repository.lowToHigh;

    }

    public void insertNote(Notes_Entity notes) {
        repository.insertNotes(notes);
    }

    public void deleteNote(int id) {
        repository.DeleteNotes(id);
    }

    public void updateNote(Notes_Entity notes) {
        repository.UpdateNotes(notes);
    }
}
