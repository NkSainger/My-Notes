package com.nikhil.mynotes.repo

import androidx.lifecycle.LiveData
import com.nikhil.mynotes.dao.NoteDao
import com.nikhil.mynotes.model.Note

class NotesRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.setNote(note)
    }

    suspend fun delete(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun update(note: Note) {
        noteDao.updateNote(note.id, note.noteTitle, note.note)
    }
}