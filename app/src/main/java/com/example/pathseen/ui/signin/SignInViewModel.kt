package com.example.pathseen.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.pathseen.data.UserRepository
import com.example.pathseen.data.ResourceRemote

class SignInViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

    private val _isSuccessSignIn: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessSignIn: LiveData<Boolean> = _isSuccessSignIn
    fun validateFields(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){
            _errorMsg.postValue("There are empty fields.")
        }
        else{
            viewModelScope.launch {
                val resourceRemote = userRepository.signInUser(email,password)
                when(resourceRemote){
                    is ResourceRemote.Success->{
                        _isSuccessSignIn.postValue(true)
                    }
                    is ResourceRemote.Error->{
                        var msg= resourceRemote.message
                        when(resourceRemote.message){
                            "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg="There is not connection"
                            "The email address is badly formatted" -> msg="The email address is badly written"
                        }
                        _errorMsg.postValue(msg!!)
                    }
                    else ->{

                    }
                }
            }
        }
    }
}