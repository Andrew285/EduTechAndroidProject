package com.example.edutechproject.features.notes_mini_app.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edutechproject.R
import com.example.edutechproject.features.notes_mini_app.data.models.Note
import com.example.edutechproject.utils.DateUtils

class NotesAdapter(private val notes: List<Note>, private val onItemClick: AdapterCallback):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTimeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes_grid, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]

        with (holder) {
            titleTextView.text = currentNote.title
            descriptionTextView.text = currentNote.description
            dateTextView.text = DateUtils.getFormattedDate(currentNote.date)
            itemView.setOnClickListener {
                onItemClick.click(position)
            }
        }
    }

    interface AdapterCallback {
        fun click(position: Int)
    }
}