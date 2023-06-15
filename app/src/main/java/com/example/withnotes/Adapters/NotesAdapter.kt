package com.example.withnotes.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.withnotes.Models.Note
import com.example.withnotes.R
import kotlin.random.Random

class NotesAdapter(private val context: Context, val listener: NotesiteClickListener) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val NoteList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       return NoteViewHolder(
           LayoutInflater.from(context).inflate(R.layout.note_viewholder,parent,false)
       )
    }

    override fun getItemCount() = NoteList.size

    fun updateList(newList: List<Note>){
        fullList.clear()
        fullList.addAll(newList)
        NoteList.clear()
        NoteList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun FilterList(search:String){
        NoteList.clear()
        for(item in fullList){
            if(item.title?.lowercase()?.contains(search.lowercase())==true ||
                    item.note?.lowercase()?.contains(search.lowercase())==true){
                NoteList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
      val currentNote  = NoteList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.note.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(RandomColor(),null))
        holder.notes_layout.setOnClickListener{
            listener.onItemClicked(NoteList[holder.adapterPosition])
        }
        holder.notes_layout.setOnLongClickListener(){
            listener.onlongItemClicked(NoteList[holder.adapterPosition], holder.notes_layout)
            true
        }
    }

    fun RandomColor() : Int{
        val list  = ArrayList<Int>()
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)
        val seed  = System.currentTimeMillis().toInt()
        val randomindex = Random(seed).nextInt(list.size)
        return R.color.white
    }

    inner class NoteViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
        val notes_layout = itemView.findViewById<CardView>(R.id.cards)
        val title  = itemView.findViewById<TextView>(R.id.title)
        val note  = itemView.findViewById<TextView>(R.id.note)
        val date  = itemView.findViewById<TextView>(R.id.date)
    }

    interface NotesiteClickListener{
        fun onItemClicked(note: Note)
        fun onlongItemClicked(note:Note,cardView: CardView)
    }
}