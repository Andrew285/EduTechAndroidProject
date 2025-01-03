package com.example.edutechproject.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {
        fun getFormattedDate(date: Date): String {
            val simpleDateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
            val formattedDate = simpleDateFormat.format(date.time)
            return formattedDate
        }

        fun getFormattedDate(date: Long): String {
            return try {
                getFormattedDate(Date(date))
            } catch (e: NumberFormatException) {
                "Invalid date format"
            }
        }
    }
}