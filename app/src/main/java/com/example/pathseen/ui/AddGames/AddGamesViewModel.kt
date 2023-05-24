package com.example.pathseen.ui.AddGames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.data.GamesRepository
import com.example.pathseen.model.Game
import kotlinx.coroutines.launch

class AddGamesViewModel : ViewModel(){

    private val gamesRepository = GamesRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _createGameSuccess: MutableLiveData<String?> = MutableLiveData()
    val createGameSuccess: LiveData<String?> = _createGameSuccess

    fun saveGame(nameGame: String, genreGame: String, scoreGame: String) {
        if(nameGame.isEmpty() || genreGame.isEmpty()){
            _errorMsg.postValue("You must fulfill all the fields.")
        }
        else{
            viewModelScope.launch {
                val game= Game(name = nameGame, genre = genreGame, score = scoreGame)
                val result = gamesRepository.saveGame(game)
                result.let{resourceRemote->
                    when(resourceRemote){
                        is ResourceRemote.Success -> {
                            _errorMsg.postValue("The game has been saved")
                            _createGameSuccess.postValue(resourceRemote.data)
                        }
                        is ResourceRemote.Error -> {
                            val msg = resourceRemote.message
                            _errorMsg.postValue(msg)
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}