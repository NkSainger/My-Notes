package com.nikhil.mynotes.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.mynotes.databinding.ItemViewBinding
import com.nikhil.mynotes.model.Note
import kotlin.random.Random

class NotesAdapter(
    private val context: Context,
    private val listener: NotesClickListener
): RecyclerView.Adapter<NotesAdapter.NotesAdapterViewHolder>() {
    private val notesList: ArrayList<Note> = ArrayList()
    private val fullList: ArrayList<Note> = ArrayList()
    inner class NotesAdapterViewHolder(
        val binding: ItemViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.apply {
                tvTitle.text = note.noteTitle
                tvTitle.isSelected = true
                tvNote.text = note.note
                tvTime.text = note.date
                tvTime.isSelected = true

                itemCardView.setCardBackgroundColor(getRandomColor())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapterViewHolder {
        return NotesAdapterViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesAdapterViewHolder, position: Int) {
        val note = notesList[position]
        holder.bind(note)

        holder.binding.itemCardView.setOnClickListener {
            listener.onItemClicked(notesList[holder.adapterPosition])
        }

        holder.binding.itemCardView.setOnLongClickListener {
            listener.onItemLongClicked(notesList[holder.adapterPosition], holder.binding.itemCardView)
            true
        }
    }

    fun updateList(newList: List<Note>) {
        fullList.clear()
        fullList.addAll(newList)

        notesList.clear()
        notesList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search: String) {
        notesList.clear()
        for (item in fullList) {
            if(item.noteTitle?.lowercase()?.contains(search.lowercase()) == true ||
                item.note?.lowercase()?.contains(search.lowercase()) == true) {
                notesList.add(item)
            }
        }

        notifyDataSetChanged()
    }

    fun getRandomColor(): Int {
        val colors = arrayOf(
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.MAGENTA,
            Color.CYAN,
            // Add more colors as needed
        )

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(colors.size)
        return colors[randomIndex]
    }

    interface NotesClickListener {
        fun onItemClicked(note: Note)
        fun onItemLongClicked(note: Note, cardView: CardView)
    }
}