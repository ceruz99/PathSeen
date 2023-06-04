package com.example.pathseen.ui.AddMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.serverMovies.model.Movie
import com.example.pathseen.serverMovies.model.MoviesList
import com.example.pathseen.serverMovies.repository.MoviesRepository
import kotlinx.coroutines.launch

class AddMoviesViewModel: ViewModel() {

    val moviesRepository = MoviesRepository()

    private val  _moviesLoaded : MutableLiveData< List<Movie>> = MutableLiveData()
    val moviesLoaded: LiveData< List<Movie>> = _moviesLoaded
    fun loadMovies() {
        viewModelScope.launch {
            val moviesList : MoviesList = moviesRepository.loadMovies()
            _moviesLoaded.postValue(moviesList.movies)
        }
    }
}

