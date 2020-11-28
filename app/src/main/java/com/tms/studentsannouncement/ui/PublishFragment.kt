package com.tms.studentsannouncement.ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.tms.studentsannouncement.*
import kotlinx.coroutines.channels.consumesAll


class PublishFragment : Fragment() {
    lateinit var textViewTitle:TextInputEditText
    lateinit var textViewDescription:TextInputEditText
    lateinit var textViewConstants:TextInputEditText
    lateinit var textViewPrice:TextInputEditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_publish, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewTitle=view.findViewById(R.id.fragment_publish_title_edit_text)
        textViewDescription=view.findViewById(R.id.fragment_publish_description_edit_text)
        textViewConstants=view.findViewById(R.id.fragment_publish_contacts_edit_text)
        textViewPrice=view.findViewById(R.id.fragment_publish_price_edit_text)
        if (Repository.selectedAnnouncement!=null) {
            Repository.selectedAnnouncement?.let {
                textViewTitle.setText(it.title)
                textViewDescription.setText(it.description)
                textViewConstants.setText(it.contacts)
                textViewPrice.setText(it.price.toString())
            }
        }
            view.findViewById<Button>(R.id.fragment_publish_button_publish).setOnClickListener{
                val announcement=Announcement(
                    title = textViewTitle.text.toString(),
                    description = textViewDescription.text.toString(),
                    price = textViewPrice.text.toString().toDouble(),
                    contacts = textViewConstants.text.toString()
                )
                if (Repository.selectedAnnouncement==null) {
                    Repository.writeNewAnnouncement(announcement)
                }else{
                    Repository.updateAnnouncement(announcement)
                    Repository.selectedAnnouncement=null
                }
                Toast.makeText(context, "Обьявление было опубликованно", Toast.LENGTH_SHORT).show()
                (context as Activity).finish()
            }
    }



}