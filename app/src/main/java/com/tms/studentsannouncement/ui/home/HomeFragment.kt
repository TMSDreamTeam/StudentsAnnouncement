package com.tms.studentsannouncement.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.tms.studentsannouncement.Announcement
import com.tms.studentsannouncement.R


class HomeFragment : Fragment() {


    private lateinit var adapter: AnnouncementFirebaseAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val query: Query = FirebaseDatabase.getInstance()
                .reference
                .child("announcements")
/*                .orderByChild("ownerId")
                .equalTo("me")*/

        val options = FirebaseRecyclerOptions.Builder<Announcement>()
                .setQuery(query) { snapshot ->
                    Announcement(
                            snapshot.child("id").value.toString(),
                            title = snapshot.child("title").value.toString(),
                            description = snapshot.child("description").value.toString(),
                            contacts = snapshot.child("contacts").value.toString()
                    )

                }

                .build()
        val recyclerView=view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager=LinearLayoutManager(context)
        adapter= AnnouncementFirebaseAdapter(options)
        recyclerView.adapter=adapter

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening();
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}