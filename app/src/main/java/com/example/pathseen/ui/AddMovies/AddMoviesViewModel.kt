package com.example.pathseen.ui.AddMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.MoviesFSRepository
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.model.MovieFS
import com.example.pathseen.serverMovies.model.Movie
import com.example.pathseen.serverMovies.model.MoviesList
import com.example.pathseen.serverMovies.repository.MoviesRepository
import kotlinx.coroutines.launch

class AddMoviesViewModel: ViewModel() {

    val moviesRepository = MoviesRepository()
    val moviesFSRepository = MoviesFSRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val  _moviesLoaded : MutableLiveData< List<Movie>> = MutableLiveData()
    val moviesLoaded: LiveData< List<Movie>> = _moviesLoaded
    fun loadMovies() {
        viewModelScope.launch {
            val moviesList : MoviesList = moviesRepository.loadMovies()
            _moviesLoaded.postValue(moviesList.movies)
        }
    }

    fun saveMovies(name : String, creator: String, score: String, imagePath: String){
        viewModelScope.launch {
            val movieFS= MovieFS(name = name, creator = creator, score = score,img = imagePath)
            val result = moviesFSRepository.saveMovie(movieFS)
            result.let{resourceRemote->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        _errorMsg.postValue("The movie has been saved")
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

