package com.tms.studentsannouncement

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class Announcement(val id:String=UUID.randomUUID().toString(),
                        val owner:String=Repository.auth.currentUser?.uid?:"",
                        val title:String="",
                        val price:Double=0.0,
                        val description: String="",
                        val contacts:String="",
                        val university:String="",
                        val faculty:String=""
)
