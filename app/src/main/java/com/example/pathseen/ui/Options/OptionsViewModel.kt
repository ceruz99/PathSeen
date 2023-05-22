package com.example.pathseen.ui.Options

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.UserRepository
import kotlinx.coroutines.launch

class OptionsViewModel : ViewModel() {
    private val userRepository = UserRepository()

    private val _text = MutableLiveData<String>().apply {
        value = "This is options Fragment"
    }
    val text: LiveData<String> = _text

    fun signOut() {
       userRepository.signOut()
    }
}