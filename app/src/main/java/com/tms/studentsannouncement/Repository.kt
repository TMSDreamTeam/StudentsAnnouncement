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
     fun writeNewAnnouncement(announcement: Announcement) {
        database.child("announcements").child(announcement.id.toString()).setValue(announcement)
    }
     fun deleteAnnouncement(announcementID: Long){
        database.child("announcements").child(announcementID.toString()).removeValue()
    }
     fun updateAnnouncement(announcement: Announcement){
        val map= mapOf(
            "id" to announcement.id,
            "ownerId" to announcement.ownerId,
            "title" to announcement.title,
            "description" to announcement.description,
            "contacts" to announcement.contacts)

        database.child("announcements").child(announcement.id.toString()).updateChildren(map)
    }
}