package com.example.pathseen.ui.AddGames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.data.GamesRepository
import com.example.pathseen.model.GameFS
import com.example.pathseen.serverGames.model.Game
import com.example.pathseen.serverGames.model.GamesList
import com.example.pathseen.serverGames.repository.GamesServerRepository
import kotlinx.coroutines.launch

class AddGamesViewModel : ViewModel(){
    val gamesRepository = GamesRepository()
    val gamesServerRepository = GamesServerRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val  _gamesLoaded : MutableLiveData< List<Game>> = MutableLiveData()
    val gamesLoaded: LiveData< List<Game>> = _gamesLoaded
    fun loadMovies() {
        viewModelScope.launch {
            val gamesList : GamesList = gamesServerRepository.loadMovies()
            _gamesLoaded.postValue(gamesList.games)
        }
    }

    fun saveGames(name : String, creator: String, score: String, imagePath: String){
        viewModelScope.launch {
            val gameFs= GameFS(name = name, creator = creator, score = score, image = imagePath)
            val result = gamesRepository.saveGame(gameFs)
            result.let{resourceRemote->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        _errorMsg.postValue("The game has been saved")
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