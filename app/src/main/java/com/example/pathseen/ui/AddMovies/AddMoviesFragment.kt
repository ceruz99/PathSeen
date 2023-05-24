package com.example.pathseen.ui.AddMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pathseen.databinding.FragmentAddGamesBinding
import com.example.pathseen.databinding.FragmentAddMoviesBinding
import com.example.pathseen.ui.AddGames.AddGamesViewModel


class AddMoviesFragment : Fragment() {
    private var _binding: FragmentAddMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val addMoviesViewModel = ViewModelProvider(this).get(AddMoviesViewModel::class.java)

        addMoviesViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
            Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_LONG).show()
        }

        addMoviesViewModel.createMovieSuccess.observe(viewLifecycleOwner){
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.saveButton.setOnClickListener(){
            val nameMovie= binding.gamesExampleEditText.text.toString()
            val genreMovie= binding.genrGamesEditText.text.toString()
            val scoreMovie= binding.scoreGamesEditText.text.toString()
            addMoviesViewModel.saveMovie(nameMovie,genreMovie,scoreMovie)
        }

        return root
    }

}