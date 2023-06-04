package com.example.pathseen.ui.Movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.MoviesFSRepository
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.model.MovieFS
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val moviesFSRepository = MoviesFSRepository()
    private var movieList: ArrayList<MovieFS> = ArrayList()

    private val _moviesList: MutableLiveData<ArrayList<MovieFS>> = MutableLiveData()
    val moviesList: LiveData<ArrayList<MovieFS>> = _moviesList

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg
    fun loadMovies() {
        movieList.clear()
        viewModelScope.launch {
            val result = moviesFSRepository.loadMovies()
            result.let{resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.documents?.forEach{document->
                            val movieFS = document.toObject<MovieFS>()
                            movieFS?.let { movieList.add(it) }
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