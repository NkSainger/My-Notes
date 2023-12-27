package com.nikhil.mynotes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes_table")
class Note {
    @PrimaryKey(autoGenerate = true)
    var id: Int ?= null

    @ColumnInfo("Note Title")
    var noteTitle: String ?= null

    @ColumnInfo("Note")
    val note: String ?= null

    @ColumnInfo("Time")
    var date: String ?= null
}