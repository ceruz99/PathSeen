package com.example.pathseen.ui.AddBooks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pathseen.databinding.FragmentAddBooksBinding
import com.example.pathseen.serverBooks.model.BookServer
import com.example.pathseen.serverBooks.model.Item
import com.example.pathseen.serverMovies.model.Movie
import com.example.pathseen.ui.AddMovies.AddMoviesAdapter

class AddBooksFragment : Fragment() {

    private var _binding: FragmentAddBooksBinding? = null
    private val binding get() = _binding!!
    private lateinit var addBooksViewModel : AddBooksViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddBooksBinding.inflate(inflater, container, false)
        addBooksViewModel = ViewModelProvider(this).get(AddBooksViewModel::class.java)
        val root: View = binding.root

        addBooksViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
            Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_LONG).show()
        }

        val listener = object : AddBooksAdapter.OnItemClickListener {
            override fun onItemClick(book: Item) {
                addBooksViewModel.saveBooks(book.bookServer.title,book.bookServer.authors[0],book.bookServer.averageRating.toString(),book.bookServer.imageLinks?.thumbnail)
                Log.d("Guardando","listo")
            }
        }

        val bookList = ArrayList<Item>()
        val addBooksAdapter = AddBooksAdapter(bookList,listener)

        binding.booksRecyclerview.apply{
            layoutManager = LinearLayoutManager(this@AddBooksFragment.requireContext())
            adapter=addBooksAdapter
            setHasFixedSize(false)
        }

        addBooksViewModel.loadMovies()

        addBooksViewModel.booksLoaded.observe(viewLifecycleOwner){listbooks->
            addBooksAdapter.appendItems(listbooks as ArrayList<BookServer>)
        }

        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}