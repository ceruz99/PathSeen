package com.example.pathseen.ui.AddMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pathseen.R
import com.example.pathseen.databinding.FragmentAddMoviesBinding
import kotlinx.coroutines.flow.callbackFlow


class AddMoviesFragment : Fragment() {
    private lateinit var addMoviesBinding: FragmentAddMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addMoviesBinding= FragmentAddMoviesBinding.inflate(inflater,container,false )
        val view=addMoviesBinding.root

        //activity?.onBackPressed()

        return view
    }

}