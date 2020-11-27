package com.tms.studentsannouncement.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.tms.studentsannouncement.Announcement
import com.tms.studentsannouncement.R

class AnnouncementFirebaseAdapter(options: FirebaseRecyclerOptions<Announcement>) :
    FirebaseRecyclerAdapter<Announcement, AnnouncementFirebaseAdapter.MyViewHolder>(options) {
    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val textViewTitle = itemView.findViewById(R.id.item_title) as TextView
        private val textViewPrice = itemView.findViewById(R.id.item_price) as TextView
        private val textViewContacts = itemView.findViewById(R.id.item_contacts) as TextView
        fun bind(announcement: Announcement) {
            textViewTitle.text = announcement.title
            textViewPrice.text = announcement.price.toString()
            textViewContacts.text = announcement.contacts


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_announcement, parent, false)
        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Announcement) {
        holder.bind(model)

    }


}