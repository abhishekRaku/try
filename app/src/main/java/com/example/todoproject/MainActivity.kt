package com.example.todoproject

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val notes = mutableListOf<Note>(Note("Abhi","Nothing",1),Note("Raju","Nothing",2))
    private lateinit var viewModel: NodeListViewModel
    private lateinit var myAdaptor: NotesListAdaptor
    private lateinit var myIntent: Intent
    private lateinit var newNotebtn: Button
    private lateinit var ivDelete: ImageView

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         viewModel = ViewModelProvider(this).get(NodeListViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setBackgroundColor(Color.WHITE)

        recyclerView.layoutManager = LinearLayoutManager(this)

        myAdaptor = NotesListAdaptor(mutableListOf())
        { noteId: Int -> goToAddAndEditNote(noteId) }

        recyclerView.adapter = myAdaptor


//        recyclerView.adapter = viewModel.nodeList.value?.let {
//            NotesListAdaptor(it){
//                noteId: Int -> goToAddAndEditNote(noteId)
//            }
//        }

//        val dummhyBtn = findViewById<Button>(R.id.button)
//        dummhyBtn.setOnClickListener {
//            viewModel.dummyNotes()
////            Log.i("Hello", viewModel.nodeList.value.toString())
//        }


    }

    override fun onResume() {
        super.onResume()
        newNotebtn = findViewById(R.id.newNoteButton)
        ivDelete = findViewById(R.id.ivDelete)
        myIntent = Intent(this, AddAndEditNote::class.java)
        Log.i("error", "why")
        viewModel.getNotes()
        viewModel.noteList.observe(this, Observer {
            Log.i("hello", viewModel.noteList.value.toString())
            myAdaptor.updateList(viewModel.noteList.value!!)
            myAdaptor.notifyDataSetChanged()
        }
        )

        newNotebtn.setOnClickListener {
            startActivity(myIntent)
        }


    }

    fun goToAddAndEditNote(id: Int){
        myIntent.putExtra("noteId",id)
        startActivity(myIntent)
    }

    fun deleteNotes(vararg ids: Int){
        ivDelete.visibility = View.VISIBLE
        ivDelete.setOnClickListener{
            for(nodeid in ids){

            }
        }
    }
}