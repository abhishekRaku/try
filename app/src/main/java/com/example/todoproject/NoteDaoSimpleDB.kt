package com.example.todoproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class NoteDaoSimpleDB : NoteDao{

    companion object{
        val db = mutableListOf<Note>()
        var i = 100
    }

    override fun addNewNote(title: String,des: String) {
            db.add(Note(title,des,i++))
    }

//    override fun editNodeTitle(id: Int, title: String) {
//        val noteMayBe = db.get(id)
//        db.set(db.indexOf(noteMayBe), Note(title, noteMayBe.description, id))
//    }
//
//    override fun editNoteDescription(id: Int, des: String) {
//        val noteMayBe = db.get(id)
//        db.set(db.indexOf(noteMayBe),Note(noteMayBe.title,des,id))
//    }

    override fun getAllNotes(): LiveData<MutableList<Note>> {
        val listNote = MutableLiveData<MutableList<Note>>()
        listNote.value = db
        Log.i("error", "db " + listNote.value.toString())
        return listNote
    }

    override fun getNoteById(id: Int): LiveData<Note> {
        val note = MutableLiveData<Note>()
        note.value = db.find { it.id == id }
        return note
    }

    override fun editNote(id: Int, note: Note) {
        Log.i("error", id.toString() + db.toString() )
        val noteMayBe = db.find { it.id==id }
        db.set(db.indexOf(noteMayBe),note)
        Log.i("error", id.toString() + db.toString() )
    }

}

// will handle edgeCases later