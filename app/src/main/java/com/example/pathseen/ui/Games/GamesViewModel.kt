package com.example.pathseen.ui.Games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GamesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aquí se almacenarán los videojuegos jugados o por jugar."
    }
    val text: LiveData<String> = _text
}