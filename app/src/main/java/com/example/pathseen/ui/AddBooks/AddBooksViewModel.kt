package com.example.pathseen.ui.AddBooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathseen.data.BooksRepository
import com.example.pathseen.data.ResourceRemote
import com.example.pathseen.model.Book
import kotlinx.coroutines.launch

class AddBooksViewModel: ViewModel() {

    private val booksRepository = BooksRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _createBookSuccess: MutableLiveData<String?> = MutableLiveData()
    val createBookSuccess: LiveData<String?> = _createBookSuccess
    fun saveBook(nameBook: String, genreBook: String,scoreBook: String){
        if(nameBook.isEmpty() || genreBook.isEmpty()){
            _errorMsg.postValue("You must write an ID and a genre.")
        }
        else{
            viewModelScope.launch {
                val book= Book(name = nameBook, genre = genreBook, score = scoreBook)
                val result = booksRepository.saveBook(book)
                result.let{resourceRemote->
                    when(resourceRemote){
                        is ResourceRemote.Success -> {
                            _errorMsg.postValue("The book has been saved")
                            _createBookSuccess.postValue(resourceRemote.data)
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

