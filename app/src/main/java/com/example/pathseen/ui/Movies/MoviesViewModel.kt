package com.example.pathseen.ui.Movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.GamesRepository
import com.example.pathseen.data.MoviesRepository
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.model.Game
import com.example.pathseen.model.Movie
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val moviesRepository = MoviesRepository()
    private var movieList: ArrayList<Movie> = ArrayList()

    private val _moviesList: MutableLiveData<ArrayList<Movie>> = MutableLiveData()
    val moviesList: LiveData<ArrayList<Movie>> = _moviesList

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg
    fun loadMovies() {
        movieList.clear()
        viewModelScope.launch {
            val result = moviesRepository.loadMovies()
            result.let{resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.documents?.forEach{document->
                            val movie = document.toObject<Movie>()
                            movie?.let { movieList.add(it) }
                        }
                        _moviesList.postValue(movieList)
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