package com.tms.studentsannouncement

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object Repository {
    lateinit var database: DatabaseReference
    lateinit var ref: DatabaseReference
    fun initRep() {
        database = Firebase.database.reference
    }
    private fun writeNewUser(announcement: Announcement) {
        database.child("announcements").child(announcement.id.toString()).setValue(announcement)
    }
}