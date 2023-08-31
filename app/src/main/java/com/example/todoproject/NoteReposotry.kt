package com.example.todoproject

import androidx.lifecycle.LiveData

class NoteReposotry(private val noteDao: NoteDao) {

    fun addNewNote(title: String, des: String){
        noteDao.addNewNote(title,des)
    }

//    fun editNodeTitle(id: Int, title: String){
//        noteDao.editNodeTitle(id,title)
//    }
//
//    fun editNoteDescription(id: Int, des: String){
//        noteDao.editNoteDescription(id,des)
//    }

    fun getAllNotes(): LiveData<MutableList<Note>>{
       return noteDao.getAllNotes()
    }

    fun getNoteById(id: Int): LiveData<Note>{
        return noteDao.getNoteById(id)
    }

    fun editNote(id: Int, note: Note){
        noteDao.editNote(id,note)
    }
}