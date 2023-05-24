package com.example.pathseen.ui.AddMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.MoviesRepository
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.model.Movie
import kotlinx.coroutines.launch

class AddMoviesViewModel: ViewModel() {
    private val moviesRepository = MoviesRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _createMovieSuccess: MutableLiveData<String?> = MutableLiveData()
    val createMovieSuccess: LiveData<String?> = _createMovieSuccess

    fun saveMovie(nameMovie: String, genreMovie: String, scoreMovie: String) {
        if(nameMovie.isEmpty() || genreMovie.isEmpty()){
            _errorMsg.postValue("You must fulfill all the fields.")
        }
        else{
            viewModelScope.launch {
                val movie= Movie(name = nameMovie, genre = genreMovie, score = scoreMovie)
                val result = moviesRepository.saveMovie(movie)
                result.let{resourceRemote->
                    when(resourceRemote){
                        is ResourceRemote.Success -> {
                            _errorMsg.postValue("The movie has been saved")
                            _createMovieSuccess.postValue(resourceRemote.data)
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