package com.example.pathseen.ui.Books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BooksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aquí se almacenarán los libros leídos o por leer."
    }
    val text: LiveData<String> = _text
}