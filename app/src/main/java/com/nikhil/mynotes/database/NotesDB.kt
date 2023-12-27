package com.nikhil.mynotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nikhil.mynotes.dao.NoteDao
import com.nikhil.mynotes.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDB: RoomDatabase() {
    abstract fun getNotesDao(): NoteDao

    companion object {
        fun getInstance(context: Context): NotesDB {
            return Room.databaseBuilder(
                context, NotesDB::class.java, "NotesManagement"
            ).build()
        }
    }
}