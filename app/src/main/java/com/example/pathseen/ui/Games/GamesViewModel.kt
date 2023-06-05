package com.example.pathseen.ui.Games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.GamesRepository
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.model.GameFS
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class GamesViewModel : ViewModel() {
    private val gamesRepository = GamesRepository()
    private var gameFSList: ArrayList<GameFS> = ArrayList()

    private val _gamesList: MutableLiveData<ArrayList<GameFS>> = MutableLiveData()
    val gamesList: LiveData<ArrayList<GameFS>> = _gamesList

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg
    fun loadGames() {
        gameFSList.clear()
        viewModelScope.launch {
            val result = gamesRepository.loadGames()
            result.let{resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.documents?.forEach{document->
                            val gameFS = document.toObject<GameFS>()
                            gameFS?.let { gameFSList.add(it) }
                        }
                        _gamesList.postValue(gameFSList)
                    }
                    is ResourceRemote.Error -> {
                        val msg= result.message
                        _errorMsg.postValue(msg)
                    }
                    else -> {

                    }
                }
            }
        }
    }

}