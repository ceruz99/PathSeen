package com.example.pathseen.ui.Books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.BooksRepository
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.model.Book
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {
    private val booksRepository = BooksRepository()
    private var bookList: ArrayList<Book> = ArrayList()

    private val _booksList: MutableLiveData<ArrayList<Book>> = MutableLiveData()
    val booksList: LiveData<ArrayList<Book>> = _booksList

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    fun loadBooks() {
        bookList.clear()
        viewModelScope.launch {
            val result = booksRepository.loadBooks()
            result.let{resourceRemote ->  
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        resourceRemote.data?.documents?.forEach{document->
                            val book = document.toObject<Book>()
                            book?.let { bookList.add(it) }
                        }
                        _booksList.postValue(bookList)
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