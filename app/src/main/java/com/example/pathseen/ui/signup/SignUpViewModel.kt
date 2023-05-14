package com.example.pathseen.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    private val _isSuccessSignUp: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessSignUp: LiveData<Boolean> = _isSuccessSignUp

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

    fun fields(name: String, email: String, password: String, repassword: String) {
        if(email.isEmpty() || name.isEmpty() || password.isEmpty() || repassword.isEmpty()){
            _errorMsg.postValue("There are empty fields.")
        }
        else{
            if(password!=repassword){
                _errorMsg.postValue("The passwords writen are not equal.")
            }
            else{
                _isSuccessSignUp.postValue(true)
            }
        }
    }


}