package com.alexprosd.dialedin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.alexprosd.dialedin.entrydata.Entry
import com.alexprosd.dialedin.entrydata.EntryDatabase
import com.example.dialedin.R

class RecyclerAdapter (private var entries: List<Entry>) :

    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

            inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val itemTitle: TextView = itemView.findViewById(R.id.titleTextView)
                val itemBean: TextView = itemView.findViewById(R.id.beanTextView)
                val itemGrind: TextView = itemView.findViewById(R.id.grindTextView)
                val itemDose: TextView = itemView.findViewById(R.id.doseTextView)
                val itemYield: TextView = itemView.findViewById(R.id.yieldTextView)
                val itemPreinfuse: TextView = itemView.findViewById(R.id.preinfuseTextView)
                val itemTime: TextView = itemView.findViewById(R.id.timeTextView)
                val itemRating: TextView = itemView.findViewById(R.id.ratingTextView)


                init {
                    itemView.setOnClickListener {
                        val position: Int = adapterPosition
                        val entry = entries[position]
                        val notes = entry.notes
                        val fragmentManager = (itemView.context as AppCompatActivity).supportFragmentManager
                        val notesDialogFragment = NotesDialogFragment(notes)
                        notesDialogFragment.show(fragmentManager, "NotesDialogFragment")
                    }

                    itemView.setOnLongClickListener {
                        val entry = entries[position]
                        val deleteDialog = DeleteDialog(entry)
                        val fragmentManager = (itemView.context as AppCompatActivity).supportFragmentManager
                        deleteDialog.show(fragmentManager, "DeleteDialog")
                        true
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return entries.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = entries[position].title
        holder.itemBean.text = entries[position].bean
        holder.itemGrind.text = entries[position].grindSize.toString()
        holder.itemDose.text = entries[position].dose.toString()
        holder.itemYield.text = entries[position].yield.toString()
        holder.itemPreinfuse.text = entries[position].preInfuse.toString()
        holder.itemTime.text = entries[position].time.toString()
        holder.itemRating.text = entries[position].rating.toString()
    }

}