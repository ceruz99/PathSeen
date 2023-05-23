package com.example.pathseen.ui.Games

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pathseen.R
import com.example.pathseen.databinding.CardViewBookItemBinding
import com.example.pathseen.model.Game

class GamesAdapter (
    private val gamesList : ArrayList<Game>,
    private val onItemClicked: (Game) -> Unit,
): RecyclerView.Adapter<GamesAdapter.GamesViewHolder>(){

    class GamesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = CardViewBookItemBinding.bind(itemView)
        fun bind(game : Game){
            with(binding){
                nameTextView.text=game.name
                genreTextView.text=game.genre
                scoreTextView.text=game.score
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_game_item,parent,false)
        return GamesAdapter.GamesViewHolder(view)
    }

    override fun getItemCount() : Int = gamesList.size

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val game = gamesList[position]
        holder.bind(gamesList[position])
        holder.itemView.setOnClickListener{onItemClicked(gamesList[position])}
    }

    fun appendItems(newList: ArrayList<Game>){
        gamesList.clear()
        gamesList.addAll(newList)
        notifyDataSetChanged()
    }
}