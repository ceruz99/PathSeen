package com.example.pathseen.ui.AddGames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pathseen.databinding.FragmentAddGamesBinding
import com.example.pathseen.serverGames.model.Game


class AddGamesFragment : Fragment() {

    private var _binding: FragmentAddGamesBinding? = null
    private val binding get() = _binding!!
    private lateinit var addGamesViewModel : AddGamesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddGamesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        addGamesViewModel = ViewModelProvider(this).get(AddGamesViewModel::class.java)

        addGamesViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
            Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_LONG).show()
        }

        val gameList =ArrayList<Game>()
        val addGamesAdapter = AddGamesAdapter(gameList, onItemClicked = {movie -> onItemClicked(movie)})

        binding.gamesRecyclerview.apply{
            layoutManager = LinearLayoutManager(this@AddGamesFragment.requireContext())
            adapter=addGamesAdapter
            setHasFixedSize(false)
        }

        addGamesViewModel.loadMovies()

        addGamesViewModel.gamesLoaded.observe(viewLifecycleOwner){listGames->
            addGamesAdapter.appendItems(listGames as ArrayList<Game>)
        }

        return root
    }

    private fun onItemClicked(game: Game){
        addGamesViewModel.saveGames(game.name,"",game.rating.toString(),game.backgroundImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}