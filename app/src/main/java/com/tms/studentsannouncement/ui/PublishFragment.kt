package com.tms.studentsannouncement.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.tms.studentsannouncement.ActivityActions
import com.tms.studentsannouncement.Announcement
import com.tms.studentsannouncement.R
import com.tms.studentsannouncement.Repository


class PublishFragment : Fragment() {
    lateinit var textViewTitle:TextInputEditText
    lateinit var textViewDescription:TextInputEditText
    lateinit var textViewConstants:TextInputEditText
    lateinit var textViewPrice:TextInputEditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (context as ActivityActions).hideBottomNavigation()
        return inflater.inflate(R.layout.fragment_publish, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewTitle=view.findViewById(R.id.fragment_publish_title)
        textViewDescription=view.findViewById(R.id.fragment_publish_description)
        textViewConstants=view.findViewById(R.id.fragment_publish_contacts)
        textViewPrice=view.findViewById(R.id.fragment_publish_price)
        if (Repository.selectedAnnouncement==null){
            view.findViewById<Button>(R.id.fragment_publish_button_publish).setOnClickListener{
                val announcement=Announcement(
                    title = textViewTitle.text.toString(),
                    description = textViewTitle.text.toString(),
                    price = textViewTitle.text.toString().toDouble(),
                    contacts = textViewConstants.text.toString()
                )
                Repository.writeNewAnnouncement(announcement)
            }
        }else{
            Repository.selectedAnnouncement?.let {
                textViewTitle.setText(it.title)
                textViewDescription.setText(it.description)
                textViewConstants.setText(it.contacts)
                textViewPrice.setText(it.price.toString())
            }
            view.findViewById<Button>(R.id.fragment_publish_button_publish).setOnClickListener{
                val announcement=Announcement(
                    id = Repository.selectedAnnouncement?.id?:"",
                    title = textViewTitle.text.toString(),
                    description = textViewTitle.text.toString(),
                    price = textViewTitle.text.toString().toDouble(),
                    contacts = textViewConstants.text.toString()
                )
                Repository.updateAnnouncement(announcement)
                Repository.selectedAnnouncement=null
            }
        }
    }



}