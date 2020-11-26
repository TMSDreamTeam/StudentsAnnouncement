package com.tms.studentsannouncement

import android.app.Application
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class App :Application(){
    override fun onCreate() {
        super.onCreate()
        Repository.initRep()
    }


}