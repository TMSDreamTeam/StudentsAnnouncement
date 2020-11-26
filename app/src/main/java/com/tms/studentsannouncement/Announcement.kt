package com.tms.studentsannouncement

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
class Announcement(var id:Long=0,var ownerID:String="",var title:String="",var description: String="",var contacts:String=""){
}