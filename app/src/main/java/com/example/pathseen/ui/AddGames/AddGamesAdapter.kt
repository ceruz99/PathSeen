package com.example.pathseen.ui.AddGames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pathseen.R
import com.example.pathseen.databinding.CardViewListsBinding
import com.example.pathseen.serverGames.model.Game
import com.squareup.picasso.Picasso

class AddGamesAdapter (
    private var gamesList: ArrayList<Game>,
    private val listener: OnItemClickListener
    ): RecyclerView.Adapter<AddGamesAdapter.GamesViewHolder>(){

        interface OnItemClickListener {
            fun onItemClick(game: Game)
        }
        class GamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            private val binding= CardViewListsBinding.bind(itemView)

            fun bindGame(game: Game, listener : OnItemClickListener){
                with(binding){
                    Picasso.get().load(game.backgroundImage).into(posterImageView)
                    titleTextView.text=game.name
                    creatorTitleTextView.text=""
                    creatorTextView.text=""
                    scoreTextView.text=game.rating.toString()
                    favoriteImageView.setOnClickListener{
                        listener.onItemClick(game)
                    }
                }
            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_lists,parent,false)
            return GamesViewHolder(itemView)
        }

        override fun getItemCount(): Int = gamesList.size

        override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
            val game = gamesList[position]
            holder.bindGame(game,listener)
            //holder.itemView.setOnClickListener{onItemClicked(game)}
        }

        fun appendItems(newList: ArrayList<Game>){
            gamesList.clear()
            gamesList.addAll(newList)
            notifyItemInserted(newList.size)
        }
}