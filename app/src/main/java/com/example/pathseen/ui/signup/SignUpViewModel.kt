package com.example.pathseen.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.data.UserRepository
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {
    private val userRepository = UserRepository()
    private val _isSuccessSignUp: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessSignUp: LiveData<Boolean> = _isSuccessSignUp

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

    fun fields(name: String, email: String, password: String, repassword: String) {
        if(email.isEmpty() || name.isEmpty() || password.isEmpty() || repassword.isEmpty()){
            _errorMsg.postValue("There are empty fields.")
        }
        else{
            if(password!=repassword || password.length<=6 || repassword.length<=6){
                _errorMsg.postValue("The passwords writen are not equal or they have less than 7 characters.")
            }
            else{
                viewModelScope.launch {
                    val result = userRepository.signUpUser(email,password)
                    result.let{resourceRemote ->
                        when(resourceRemote){
                            is ResourceRemote.Success->{
                                _isSuccessSignUp.postValue(true)
                            }
                            is ResourceRemote.Error->{
                                var msg= resourceRemote.message
                                when(resourceRemote.message){
                                    "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> msg="There is not connection"
                                    "The email address is already in use by another account." -> msg="There is an account already with that email."
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
    }


}