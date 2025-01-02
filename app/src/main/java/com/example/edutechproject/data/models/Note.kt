package com.example.edutechproject.data.models

data class Note(
    val id: Int,
    val title: String,
    val description: String,
    val date: Long = System.currentTimeMillis()
)