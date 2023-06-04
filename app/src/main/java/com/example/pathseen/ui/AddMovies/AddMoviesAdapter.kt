package com.example.pathseen.ui.AddMovies

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.pathseen.R
import com.example.pathseen.databinding.CardViewListsBinding
import com.example.pathseen.serverMovies.model.Movie
import com.squareup.picasso.Picasso

class AddMoviesAdapter (
    private var moviesList: ArrayList<Movie>,
    private val onItemClicked: (Movie) -> Unit
    ): RecyclerView.Adapter<AddMoviesAdapter.MoviesViewHolder>(){

        class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            private val binding= CardViewListsBinding.bind(itemView)

            fun bindMovie(movie: Movie){
                with(binding){
                    Picasso.get().load("https://image.tmdb.org/t/p/original"+movie.backdropPath).into(posterImageView)
                    titleTextView.text=movie.title
                    creatorTitleTextView.text=""
                    creatorTextView.text=""
                    scoreTextView.text=movie.voteAverage.toString()
                    imageView6.setOnClickListener{

                    }
                }
            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_lists,parent,false)
            return MoviesViewHolder(itemView)
        }

        override fun getItemCount(): Int = moviesList.size

        override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
            val movie = moviesList[position]
            holder.bindMovie(movie)
            holder.itemView.setOnClickListener{onItemClicked(movie)}
        }

        fun appendItems(newList: ArrayList<Movie>){
            moviesList.clear()
            moviesList.addAll(newList)
            notifyItemInserted(newList.size)
        }
}