package com.example.edutechproject.features.home

import androidx.fragment.app.Fragment

data class HomeFeatureModel(
    val featureName: String,
    val fragmentClass: Class<out Fragment>
)