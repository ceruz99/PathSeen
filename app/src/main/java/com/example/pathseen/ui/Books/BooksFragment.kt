package com.example.pathseen.ui.Books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pathseen.R
import com.example.pathseen.databinding.FragmentBooksBinding
import com.example.pathseen.model.Book


class BooksFragment : Fragment() {

private var _binding: FragmentBooksBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
    private lateinit var booksAdapter: BooksAdapter
    private var booksList: ArrayList<Book> = ArrayList()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    val booksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

      _binding = FragmentBooksBinding.inflate(inflater, container, false)
      val root: View = binding.root


      binding.addBooksButton.setOnClickListener{
          findNavController().navigate(R.id.action_navigation_books_to_addBooksFragment)
      }

      booksAdapter= BooksAdapter(booksList,
      onItemClicked = {  })

      binding.booksRecyclerView.apply{
          layoutManager = LinearLayoutManager(this@BooksFragment.requireContext())
          adapter=booksAdapter
          setHasFixedSize(false)
      }

      booksViewModel.loadBooks()

      booksViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
          Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
      }

      booksViewModel.booksList.observe(viewLifecycleOwner){bookList->
            booksAdapter.appendItems(bookList)
      }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}