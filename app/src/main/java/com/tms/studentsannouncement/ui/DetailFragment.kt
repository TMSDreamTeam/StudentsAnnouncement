package com.tms.studentsannouncement.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tms.studentsannouncement.R
import com.tms.studentsannouncement.Repository.selectedAnnouncement
import com.google.firebase.auth.FirebaseAuth.*
import com.tms.studentsannouncement.ActivityActions
import com.tms.studentsannouncement.Repository.deleteAnnouncement

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (context as ActivityActions).hideBottomNavigation()
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.text_view_title)
        val description = view.findViewById<TextView>(R.id.text_view_description)
        val price = view.findViewById<TextView>(R.id.text_view_price)
        val contacts = view.findViewById<TextView>(R.id.text_view_contacts)
        val button_revers = view.findViewById<Button>(R.id.image_button_revers)
        val button_delete = view.findViewById<Button>(R.id.image_button_delete)

        title.text = selectedAnnouncement?.title
        description.text = selectedAnnouncement?.description
        price.text = selectedAnnouncement?.price.toString()
        contacts.text = selectedAnnouncement?.contacts
        if (selectedAnnouncement?.owner != getInstance().currentUser?.uid) {
            button_revers.visibility = View.GONE
            button_delete.visibility = View.GONE
        }
        button_delete.setOnClickListener {
            selectedAnnouncement?.id?.let { it1 ->
                deleteAnnouncement(it1)
                Toast.makeText(context, "Ваше обьявление удалено", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
        button_revers.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_publishFragment)
        }
    }
}