package com.example.edutechproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutechproject.R
import com.example.edutechproject.models.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SharedPrefsViewModel(application: Application) : AndroidViewModel(application) {
    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> = _userData

    lateinit var colors: List<Int>

    fun fetchUserData() {
        viewModelScope.launch {
            try {
                val fetchedUserData = withContext(Dispatchers.IO) {
                    getUserDataFromRepository()
                }
                _userData.value = fetchedUserData
            }
            catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getUserDataFromRepository(): UserData {
        return UserData("John Doe", 25, R.color.red, true) // Example data
    }

    fun fetchColors() {
        colors = listOf(
            R.color.red,
            R.color.blue,
            R.color.green
        )
    }

    fun selectColor(color: Int): Int {
        return colors.indexOf(colors.first { it == color })
    }
}