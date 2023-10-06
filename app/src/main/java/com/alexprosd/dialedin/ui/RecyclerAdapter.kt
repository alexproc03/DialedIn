package com.alexprosd.dialedin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexprosd.dialedin.entrydata.Entry
import com.example.dialedin.R

class RecyclerAdapter (private var entries: List<Entry>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
            inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val itemTitle: TextView = itemView.findViewById(R.id.titleTextView)
                val itemDate: TextView = itemView.findViewById(R.id.dateTextView)
                val itemBean: TextView = itemView.findViewById(R.id.beanTextView)
                val itemRating: TextView = itemView.findViewById(R.id.ratingTextView)
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
        holder.itemDate.text = "placeholder";
        holder.itemBean.text = entries[position].bean
        holder.itemRating.text = entries[position].rating.toString()
    }
}