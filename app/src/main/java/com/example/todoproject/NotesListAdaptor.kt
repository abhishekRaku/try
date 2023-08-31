package com.example.todoproject

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NotesListAdaptor(private val notes: MutableList<Note>, private val goTo: (Int) -> Unit): RecyclerView.Adapter<NotesViewHolder>() {

    private val selectedNotes = mutableListOf<Note>()
    private var unSelectedNotes = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val notesItems = layoutInflater.inflate(R.layout.list_layout,parent,false)
        return NotesViewHolder(notesItems)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bindData(notes[position])
        holder.onLongSelect()
        holder.goToAddAndEditNoteActivity(notes[position].id,goTo)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateList(updateNotes: MutableList<Note>){
        notes.clear()
        notes.addAll(updateNotes)
    }
}

class NotesViewHolder( private val view: View): RecyclerView.ViewHolder(view){
    private val noteTitle: TextView = view.findViewById(R.id.tvNoteTitle)
    private val noteDes: TextView =view.findViewById(R.id.tvNoteDes)
    private val linearLayout = view.findViewById<LinearLayout>(R.id.NoteLinearLayout)

    fun bindData(note: Note){
        noteTitle.text = note.title
        noteDes.text = note.description
    }

    fun onLongSelect(){
        view.setOnLongClickListener{
            Log.i("hello","sdfs")
            linearLayout.setBackgroundColor(Color.RED)
            true
        }
    }

    fun goToAddAndEditNoteActivity(id: Int, goTo: (Int) -> Unit){
        view.setOnClickListener {
            goTo(id)
        }
    }

}