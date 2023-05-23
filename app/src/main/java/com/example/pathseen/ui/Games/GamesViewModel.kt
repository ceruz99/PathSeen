package com.example.pathseen.ui.Games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.GamesRepository
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.model.Game
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class GamesViewModel : ViewModel() {
    private val gamesRepository = GamesRepository()
    private var gameList: ArrayList<Game> = ArrayList()

    private val _gamesList: MutableLiveData<ArrayList<Game>> = MutableLiveData()
    val gamesList: LiveData<ArrayList<Game>> = _gamesList

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg
    fun loadGames() {
        gameList.clear()
        viewModelScope.launch {
            val result = gamesRepository.loadGames()
            result.let{resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.documents?.forEach{document->
                            val game = document.toObject<Game>()
                            game?.let { gameList.add(it) }
                        }
                        _gamesList.postValue(gameList)
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