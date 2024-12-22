package com.example.edutechproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edutechproject.R
import com.example.edutechproject.features.home.HomeFeatureModel

class HomeRecyclerViewAdapter(private val featuresList: List<HomeFeatureModel>): RecyclerView.Adapter<HomeRecyclerViewAdapter.FeatureViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener


    class FeatureViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val featureNameTextView: TextView = view.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_feature, parent, false)
        return FeatureViewHolder(view)
    }

    override fun getItemCount(): Int {
        return featuresList.size
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val currentFeature = featuresList[position]
        holder.featureNameTextView.text = currentFeature.featureName
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position, currentFeature)
        }
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        onItemClickListener = clickListener
    }

    interface OnItemClickListener {
        fun onClick(position: Int, featureModel: HomeFeatureModel)
    }
}