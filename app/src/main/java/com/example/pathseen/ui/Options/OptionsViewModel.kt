package com.example.pathseen.ui.Options

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.data.UserRepository
import com.example.pathseen.model.User
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class OptionsViewModel : ViewModel() {
    private val userRepository = UserRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _userLoaded: MutableLiveData<User?> = MutableLiveData()
    val userLoaded: LiveData<User?> = _userLoaded

    fun signOut() {
       userRepository.signOut()
    }

    fun loadUserInfo() {
        viewModelScope.launch {
            val result = userRepository.loadUserInfo()
            result.let{resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach(){document->
                            val user = document.toObject<User>()
                            if(user?.uid==userRepository.getUIDCurrentUser()){
                                _userLoaded.postValue(user)
                            }
                        }
                    }
                    is ResourceRemote.Error -> {
                        val msg = result.message
                        _errorMsg.postValue(msg)
                    }
                    else -> {

                    }
                }
            }
        }
    }
}