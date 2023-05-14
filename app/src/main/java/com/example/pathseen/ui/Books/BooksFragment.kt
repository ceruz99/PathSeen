package com.example.pathseen.ui.Books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pathseen.R
import com.example.pathseen.databinding.FragmentBooksBinding


class BooksFragment : Fragment() {

private var _binding: FragmentBooksBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val booksViewModel =
            ViewModelProvider(this).get(BooksViewModel::class.java)

    _binding = FragmentBooksBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textBooks
    booksViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }

      binding.addBooksButton.setOnClickListener{
          findNavController().navigate(R.id.action_navigation_books_to_addBooksFragment)
      }

    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}