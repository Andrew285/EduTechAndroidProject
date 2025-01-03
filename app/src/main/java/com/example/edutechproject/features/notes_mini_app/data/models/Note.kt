package com.example.edutechproject.features.notes_mini_app.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: Long = System.currentTimeMillis()
) : Parcelable