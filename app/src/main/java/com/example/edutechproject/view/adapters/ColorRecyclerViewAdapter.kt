package com.example.edutechproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.edutechproject.R

class ColorRecyclerViewAdapter(
    val colors: List<Int>
): RecyclerView.Adapter<ColorRecyclerViewAdapter.ColorViewHolder>() {


    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val currentColor = colors[position]

        holder.itemView.background = currentColor.toDrawable()
    }
}