package com.example.pathseen.ui.Movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pathseen.R
import com.example.pathseen.databinding.CardViewBookItemBinding
import com.example.pathseen.model.Movie

class MoviesAdapter(
    private val moviesList : ArrayList<Movie>,
    private val onItemClicked: (Movie) -> Unit,
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){


    class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = CardViewBookItemBinding.bind(itemView)
        fun bind(movie: Movie){
            with(binding){
                nameTextView.text=movie.name
                genreTextView.text=movie.genre
                scoreTextView.text=movie.score
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_movie_item,parent,false)
        return MoviesAdapter.MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(moviesList[position])
        holder.itemView.setOnClickListener{onItemClicked(moviesList[position])}
    }

    fun appendItems(newList: ArrayList<Movie>){
        moviesList.clear()
        moviesList.addAll(newList)
        notifyDataSetChanged()
    }
}