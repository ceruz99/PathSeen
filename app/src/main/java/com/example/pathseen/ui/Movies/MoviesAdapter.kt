package com.example.pathseen.ui.Movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pathseen.R
import com.example.pathseen.databinding.CardViewBookItemBinding
import com.example.pathseen.model.MovieFS
import com.squareup.picasso.Picasso

class MoviesAdapter(
    private val moviesList : ArrayList<MovieFS>,
    private val onItemClicked: (MovieFS) -> Unit,
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){


    class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = CardViewBookItemBinding.bind(itemView)
        fun bind(movieFS: MovieFS){
            with(binding){
                nameTextView.text=movieFS.name
                genreTextView.text=movieFS.creator
                scoreTextView.text=movieFS.score
                Picasso.get().load(movieFS.img).into(imageView4)
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

    fun appendItems(newList: ArrayList<MovieFS>){
        moviesList.clear()
        moviesList.addAll(newList)
        notifyDataSetChanged()
    }
}