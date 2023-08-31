package com.example.todoproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class AddAndEditNote : AppCompatActivity() {
    private lateinit var editTitle: EditText
    private lateinit var editDes: EditText

    private lateinit var noteSaveBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_and_edit_note)
    }

    override fun onResume() {
        super.onResume()
        editTitle = findViewById(R.id.etvNoteTitle)
        editDes = findViewById(R.id.etvNoteDes)
        noteSaveBtn = findViewById(R.id.NoteSaveButton)



        val viewModel = ViewModelProvider(this).get(NodeListViewModel::class.java)

        val id = intent.getIntExtra("noteId",0)

        Log.i("hello", id.toString())

        if(id!=0) {
            viewModel.dummy.observe(this, Observer {
                Log.i("hello", "note by id ${viewModel.noteById.value.toString()}")
                viewModel.getNoteById(id)
                val note = viewModel.noteById
                Log.i("hello", "2 note by id ${viewModel.noteById.value.toString()}")
                editTitle.setText(note.value?.title)
                editDes.setText(note.value?.description)
            })
        }

        noteSaveBtn.setOnClickListener {
            if(editTitle.text.isEmpty()){
                Toast.makeText(this,"Please Enter title",Toast.LENGTH_SHORT).show()
            }
            else if(id!=0) {
                viewModel.editNote(id, Note(editTitle.text.toString(), editDes.text.toString(), id))
            }else{
                viewModel.newNote(editTitle.text.toString(),editDes.text.toString())
            }
        }
    }
}