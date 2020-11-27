package com.tms.studentsannouncement.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tms.studentsannouncement.Announcement

object Myviewmodel:ViewModel() {

    var selectedAnnouncement =MutableLiveData<Announcement?>(null)
}