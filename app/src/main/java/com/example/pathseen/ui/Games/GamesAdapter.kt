package com.example.pathseen.ui.Games

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pathseen.R
import com.example.pathseen.databinding.CardViewBookItemBinding
import com.example.pathseen.model.GameFS
import com.squareup.picasso.Picasso

class GamesAdapter (
    private val gamesList : ArrayList<GameFS>,
    private val onItemClicked: (GameFS) -> Unit,
): RecyclerView.Adapter<GamesAdapter.GamesViewHolder>(){

    class GamesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = CardViewBookItemBinding.bind(itemView)
        fun bind(gameFS : GameFS){
            with(binding){
                nameTextView.text=gameFS.name
                genreTextView.text=gameFS.creator
                scoreTextView.text=gameFS.score
                Picasso.get().load(gameFS.image).into(imageView4)
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

    fun appendItems(newList: ArrayList<GameFS>){
        gamesList.clear()
        gamesList.addAll(newList)
        notifyDataSetChanged()
    }
}