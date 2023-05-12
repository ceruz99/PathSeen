package com.example.pathseen.ui.Movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aquí se almacenarán las películas vistas y por ver."
    }
    val text: LiveData<String> = _text
}