package com.example.pathseen.ui.Games

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
import com.example.pathseen.databinding.FragmentGamesBinding
import com.example.pathseen.model.GameFS

class GamesFragment : Fragment() {

    private var _binding: FragmentGamesBinding? = null

      private val binding get() = _binding!!
      private lateinit var gamesAdapter: GamesAdapter
      private var gamesList: ArrayList<GameFS> = ArrayList()
      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

          val gamesViewModel = ViewModelProvider(this).get(GamesViewModel::class.java)

          _binding = FragmentGamesBinding.inflate(inflater, container, false)
          val root: View = binding.root

          binding.addGamesButton.setOnClickListener{
              findNavController().navigate(R.id.action_navigation_games_to_addGamesFragment)
          }

          gamesAdapter= GamesAdapter(gamesList,
              onItemClicked = {  })

          binding.gamesRecyclerView.apply{
              layoutManager = LinearLayoutManager(this@GamesFragment.requireContext())
              adapter=gamesAdapter
              setHasFixedSize(false)
          }

          gamesViewModel.loadGames()

          gamesViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
              Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
          }

          gamesViewModel.gamesList.observe(viewLifecycleOwner){gameList->
              gamesAdapter.appendItems(gameList)
          }
        return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}