package com.example.pathseen.ui.AddGames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pathseen.databinding.FragmentAddGamesBinding
import com.example.pathseen.ui.AddGames.AddGamesViewModel


class AddGamesFragment : Fragment() {

    private var _binding: FragmentAddGamesBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddGamesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val addGamesViewModel = ViewModelProvider(this).get(AddGamesViewModel::class.java)

        addGamesViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
            Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_LONG).show()
        }

        addGamesViewModel.createGameSuccess.observe(viewLifecycleOwner){
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.saveButton.setOnClickListener(){
            val nameGame= binding.gamesExampleEditText.text.toString()
            val genreGame= binding.genrGamesEditText.text.toString()
            val scoreGame= binding.scoreGamesEditText.text.toString()
            addGamesViewModel.saveBook(nameGame,genreGame,scoreGame)
        }

        return root
    }
}