package com.example.pathseen.ui.AddBooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.BooksRepository
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.model.Book
import com.example.pathseen.serverBooks.model.BookServer
import com.example.pathseen.serverBooks.model.BooksList
import com.example.pathseen.serverBooks.model.Item
import com.example.pathseen.serverBooks.repository.BooksServerRepository
import kotlinx.coroutines.launch

class AddBooksViewModel: ViewModel() {

    private val booksServerRepository = BooksServerRepository()
    private val booksRepository = BooksRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _booksLoaded : MutableLiveData<List<Item>> = MutableLiveData()
    val booksLoaded : LiveData<List<Item>> = _booksLoaded

    fun loadMovies() {
        viewModelScope.launch {
            val booksList : BooksList = booksServerRepository.loadBooks()
            _booksLoaded.postValue(booksList.items)
        }
    }

    fun saveBooks(title: String, author: String, score: String, poster: String?) {
        viewModelScope.launch {
            val book= Book(name = title, author = author, score = score, image = poster)
            val result = booksRepository.saveBook(book)
            result.let{resourceRemote->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        _errorMsg.postValue("The bookServer has been saved")
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

