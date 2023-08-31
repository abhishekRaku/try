package com.example.todoproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


interface NoteDao {

    fun addNewNote(title: String, des: String)

//    fun editNodeTitle(id: Int, title: String)
//
//    fun editNoteDescription(id: Int, des: String)

    fun getAllNotes(): LiveData<MutableList<Note>>

    fun getNoteById(id: Int): LiveData<Note>

    fun editNote(id: Int, note: Note)
}