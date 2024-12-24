package com.example.edutechproject.view.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edutechproject.R
import com.example.edutechproject.data.models.ContactModel

class ContactRecyclerViewAdapter(
    private val contactList: List<ContactModel>
):
    RecyclerView.Adapter<ContactRecyclerViewAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val phoneTextView: TextView = itemView.findViewById(R.id.phoneTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contactList[position]

        holder.nameTextView.text = currentContact.displayName
        holder.phoneTextView.text = currentContact.displayPhone
    }
}