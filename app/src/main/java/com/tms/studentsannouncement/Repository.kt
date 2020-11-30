package com.tms.studentsannouncement

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object Repository {
    lateinit var database: DatabaseReference
    lateinit var auth: FirebaseAuth
    var selectedAnnouncement:Announcement?=null
    fun initRep() {
        auth= FirebaseAuth.getInstance()
        database = Firebase.database.reference
    }
     fun writeNewAnnouncement(announcement: Announcement) {
        database.child("announcements").child(announcement.id).setValue(announcement)
        database.child("users/${auth.currentUser?.uid}").child(announcement.id).setValue(announcement)
    }
     fun deleteAnnouncement(announcementID: String){
        database.child("announcements").child(announcementID).removeValue()
         database.child("users/${auth.currentUser?.uid}").child(announcementID).removeValue()
    }
     fun updateAnnouncement(announcement: Announcement){
        val map= mapOf(
            "id" to announcement.id,
            "owner" to announcement.owner,
            "title" to announcement.title,
            "price" to announcement.price,
            "description" to announcement.description,
            "contacts" to announcement.contacts,
            "university" to announcement.university,
            "faculty" to announcement.faculty)

        database.child("announcements").child(announcement.id).updateChildren(map)
         database.child("users/${auth.currentUser?.uid}").child(announcement.id).updateChildren(map)
    }
}