package com.example.pathseen.ui.AddMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pathseen.databinding.FragmentAddMoviesBinding
import com.example.pathseen.serverMovies.model.Movie


class AddMoviesFragment : Fragment() {
    private var _binding: FragmentAddMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var addMoviesViewModel : AddMoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddMoviesBinding.inflate(inflater, container, false)
        addMoviesViewModel = ViewModelProvider(this).get(AddMoviesViewModel::class.java)
        val root: View = binding.root

        addMoviesViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
            Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_LONG).show()
        }

        val movieList =ArrayList<Movie>()
        val addMoviesAdapter = AddMoviesAdapter(movieList, onItemClicked = {movie -> onItemClicked(movie)})

        binding.moviesRecyclerview.apply{
            layoutManager = LinearLayoutManager(this@AddMoviesFragment.requireContext())
            adapter=addMoviesAdapter
            setHasFixedSize(false)
        }

        addMoviesViewModel.loadMovies()

        addMoviesViewModel.moviesLoaded.observe(viewLifecycleOwner){listMovies->
            addMoviesAdapter.appendItems(listMovies as ArrayList<Movie>)
        }

        return root
    }

    private fun onItemClicked(movie: Movie){
        addMoviesViewModel.saveMovies(movie.title,"",movie.voteAverage.toString(),"https://image.tmdb.org/t/p/original"+movie.posterPath)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}