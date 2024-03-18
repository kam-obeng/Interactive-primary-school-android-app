package com.example.afinal

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class NotesActivity : AppCompatActivity() {
    private lateinit var notesAdapter: ArrayAdapter<String>
    private lateinit var notesList: MutableList<String>
    private var selectedNotePosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val editTextNote = findViewById<EditText>(R.id.editTextNote)
        val buttonCreateNote = findViewById<Button>(R.id.buttonCreateNote)
        val listViewNotes = findViewById<ListView>(R.id.listViewNotes)

        notesList = mutableListOf()
        notesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notesList)
        listViewNotes.adapter = notesAdapter

        buttonCreateNote.setOnClickListener {
            val newNote = editTextNote.text.toString()
            if (newNote.isNotEmpty()) {
                notesList.add(newNote)
                notesAdapter.notifyDataSetChanged()
                editTextNote.text.clear()
            }
        }

        listViewNotes.setOnItemClickListener { _, _, position, _ ->
            selectedNotePosition = position
            editTextNote.setText(notesList[position])
        }

        listViewNotes.setOnItemLongClickListener { _, _, position, _ ->
            // Implement code to delete note on long press
            notesList.removeAt(position)
            notesAdapter.notifyDataSetChanged()
            true
        }
    }
}
