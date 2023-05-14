package com.example.pathseen.ui.Games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pathseen.R
import com.example.pathseen.databinding.FragmentGamesBinding

class GamesFragment : Fragment() {

private var _binding: FragmentGamesBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val gamesViewModel =
            ViewModelProvider(this).get(GamesViewModel::class.java)

    _binding = FragmentGamesBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textGames
    gamesViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }

      binding.addGamesButton.setOnClickListener{
          findNavController().navigate(R.id.action_navigation_games_to_addGamesFragment)
      }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}