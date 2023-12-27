package com.nikhil.mynotes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nikhil.mynotes.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setNote(note: Note)

    @Query("UPDATE notes_table Set `Note Title` = :title, `Note` = :note WHERE `id` = :id")
    suspend fun updateNote(id: Int?, title: String?, note: String?)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}