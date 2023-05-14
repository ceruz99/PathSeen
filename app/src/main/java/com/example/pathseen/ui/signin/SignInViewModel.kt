package com.example.pathseen.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

    private val _isSuccessSignIn: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessSignIn: LiveData<Boolean> = _isSuccessSignIn
    fun validateFields(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){
            _errorMsg.postValue("There are empty fields.")
        }
        else{
            _isSuccessSignIn.postValue(true)
        }
    }
}