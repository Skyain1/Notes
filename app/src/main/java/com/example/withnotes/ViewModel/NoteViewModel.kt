package com.example.withnotes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.withnotes.Database.NoteDatabase
import com.example.withnotes.Database.NotesRepository
import com.example.withnotes.Models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : NotesRepository
    val allnotes : LiveData<List<Note>>
    init {
        val dao  = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NotesRepository(dao)
        allnotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch (Dispatchers.IO ){
        repository.delete(note)
    }
    fun insertNote(note: Note) = viewModelScope.launch  (Dispatchers.IO){
        repository.insert(note)
    }
    fun update(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.update(note)
    }

}