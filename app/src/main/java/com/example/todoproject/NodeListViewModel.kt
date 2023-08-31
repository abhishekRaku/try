package com.example.todoproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope

class NodeListViewModel: ViewModel() {

    var noteList = MutableLiveData<MutableList<Note>>()
    val noteReposotry = NoteReposotry(NoteDaoSimpleDB())
    val dummy = MutableLiveData<String>()

    var noteById = MutableLiveData<Note>()

    init {
        noteList = noteReposotry.getAllNotes()  as MutableLiveData<MutableList<Note>>
//        noteReposotry.addNewNote("Dummy Note","Dummy Text2")
        dummy.value = "Abhi"
    }

    fun dummyNotes(){
        noteList.value?.add(Note("Abhi","Nothing",1))
        noteList.value?.add(Note("Raju","Nothing",2))
        dummy.value = "Abhishek"
    }

    fun getNotes(){
        noteList = noteReposotry.getAllNotes() as MutableLiveData<MutableList<Note>>
        Log.i("error", noteList.value.toString())
    }

    fun getNoteById(id: Int){
        noteById = noteReposotry.getNoteById(id) as MutableLiveData<Note>
    }

    fun editNote(id: Int, note: Note){
        noteReposotry.editNote(id,note)
    }

    fun newNote(title: String, des: String){
        noteReposotry.addNewNote(title,des)
    }

}