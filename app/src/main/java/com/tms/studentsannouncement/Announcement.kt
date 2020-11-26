package com.tms.studentsannouncement

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class Announcement(val id:String=UUID.randomUUID().toString(),
                   val ownerId:String="",
                   val title:String="",
                   val description: String="",
                   val contacts:String=""){
}