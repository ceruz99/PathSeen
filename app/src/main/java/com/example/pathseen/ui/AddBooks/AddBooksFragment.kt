package com.example.pathseen.ui.AddBooks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pathseen.databinding.FragmentAddBooksBinding

class AddBooksFragment : Fragment() {

    private var _binding: FragmentAddBooksBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBooksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val addBooksViewModel = ViewModelProvider(this).get(AddBooksViewModel::class.java)

        addBooksViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
            Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_LONG).show()
        }

        addBooksViewModel.createBookSuccess.observe(viewLifecycleOwner){
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.saveButton.setOnClickListener(){
            val nameBook= binding.booksExampleEditText.text.toString()
            val genreBook= binding.genreBooksEditText.text.toString()
            val scoreBook= binding.scoreBooksEditText.text.toString()
            addBooksViewModel.saveBook(nameBook,genreBook,scoreBook)
        }

        return root


    }

}