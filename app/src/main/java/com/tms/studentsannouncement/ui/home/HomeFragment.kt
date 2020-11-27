package com.tms.studentsannouncement.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.Query
import com.tms.studentsannouncement.Announcement
import com.tms.studentsannouncement.R
import com.tms.studentsannouncement.Repository
import com.tms.studentsannouncement.ui.AnnouncementFirebaseAdapter


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
        val query: Query = Repository.database
                .child("announcements")

        val options = FirebaseRecyclerOptions.Builder<Announcement>()
                .setQuery(query) { snapshot ->
                    Announcement(
                            id=snapshot.child("id").value.toString(),
                            title = snapshot.child("title").value.toString(),
                            price =  snapshot.child("price").value.toString().toDouble(),
                            description=snapshot.child("description").value.toString(),
                            contacts = snapshot.child("contacts").value.toString(),
                            university = snapshot.child("university").value.toString(),
                            faculty = snapshot.child("faculty").value.toString()
                    )

                }

                .build()
        val recyclerView=view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager=LinearLayoutManager(context)
        adapter=
            AnnouncementFirebaseAdapter(options)
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