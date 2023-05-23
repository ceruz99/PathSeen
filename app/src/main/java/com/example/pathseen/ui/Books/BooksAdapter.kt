package com.example.pathseen.ui.Books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pathseen.R
import com.example.pathseen.databinding.CardViewBookItemBinding
import com.example.pathseen.model.Book

class BooksAdapter (
    private val booksList : ArrayList<Book>,
    private val onItemClicked: (Book) -> Unit,
        ) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_book_item,parent,false)
        return BooksViewHolder(view)
    }

    override fun getItemCount(): Int = booksList.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = booksList[position]
        holder.bind(booksList[position])
        holder.itemView.setOnClickListener{onItemClicked(booksList[position])}
    }

    fun appendItems(newList: ArrayList<Book>){
        booksList.clear()
        booksList.addAll(newList)
        notifyDataSetChanged()
    }

    class BooksViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = CardViewBookItemBinding.bind(itemView)
        fun bind(book: Book){
            with(binding){
                nameTextView.text=book.name
                genreTextView.text=book.genre
                scoreTextView.text=book.score
            }
        }
    }
}