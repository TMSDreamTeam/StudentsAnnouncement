package com.tms.studentsannouncement.ui

import android.R.attr.label
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.tms.studentsannouncement.R
import com.tms.studentsannouncement.Repository.auth
import com.tms.studentsannouncement.Repository.deleteAnnouncement
import com.tms.studentsannouncement.Repository.selectedAnnouncement


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = view.findViewById<TextView>(R.id.title_fragment_detail)
        val description = view.findViewById<TextView>(R.id.description_fragment_detail)
        val price = view.findViewById<TextView>(R.id.price_fragment_detail)
        val contacts = view.findViewById<TextView>(R.id.contacts_fragment_detail)
        contacts.setOnClickListener{
            val clipboard: ClipboardManager? =
                activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText(contacts.text, contacts.text)
            clipboard?.setPrimaryClip(clip)
            Toast.makeText(context, "Скопированно в буфер обмена", Toast.LENGTH_SHORT).show()
        }
        val buttonRedact = view.findViewById<MaterialButton>(R.id.edit_fragment_detail)
        val buttonDelete = view.findViewById<MaterialButton>(R.id.delete_fragment_detail)

        title.text = selectedAnnouncement?.title
        description.text = selectedAnnouncement?.description
        price.text = "${selectedAnnouncement?.price.toString()}р"
        contacts.text= selectedAnnouncement?.contacts
        if (selectedAnnouncement?.owner != auth.currentUser?.uid) {
            buttonRedact.visibility = View.GONE
            buttonDelete.visibility = View.GONE
        }
        buttonDelete.setOnClickListener {
            selectedAnnouncement?.id?.let { it1 ->
                deleteAnnouncement(it1)
                Toast.makeText(context, "Ваше обьявление удалено", Toast.LENGTH_SHORT).show()
                (context as Activity).finish()            }
        }
        buttonRedact.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_detail_to_navigation_publish)
        }
    }

}