package com.tms.studentsannouncement.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tms.studentsannouncement.R

class AddAnnouncementFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_announcement, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        return root
    }
}