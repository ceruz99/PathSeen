package com.example.pathseen.ui.Movies

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
import com.example.pathseen.databinding.FragmentMoviesBinding
import com.example.pathseen.model.MovieFS


class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter
    private var moviesList: ArrayList<MovieFS> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.addMoviesButton.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_movies_to_addMoviesFragment)
        }

        moviesAdapter= MoviesAdapter(moviesList,
            onItemClicked = {  })

        binding.gamesRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@MoviesFragment.requireContext())
            adapter=moviesAdapter
            setHasFixedSize(false)
        }

        moviesViewModel.loadMovies()

        moviesViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
        }

        moviesViewModel.moviesList.observe(viewLifecycleOwner){movieList->
            moviesAdapter.appendItems(movieList)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}