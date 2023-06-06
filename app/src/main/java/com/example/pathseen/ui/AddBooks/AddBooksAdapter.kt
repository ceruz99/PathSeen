package com.example.pathseen.ui.AddBooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pathseen.R
import com.example.pathseen.databinding.CardViewListsBinding
import com.example.pathseen.serverBooks.model.BookServer
import com.example.pathseen.serverBooks.model.Item


class AddBooksAdapter (
    private var booksList: ArrayList<Item>,
    private val listener : OnItemClickListener
        ): RecyclerView.Adapter<AddBooksAdapter.BooksViewHolder>(){
    interface OnItemClickListener {
        fun onItemClick(book: Item)
    }
    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding= CardViewListsBinding.bind(itemView)
        private val context = itemView.context
        fun bindBook(book: Item,listener: OnItemClickListener){
            with(binding){
                Glide.with(context).load(book.bookServer.imageLinks?.thumbnail).into(posterImageView)
                titleTextView.text=book.bookServer.title
                creatorTitleTextView.text="Author"
                creatorTextView.text=book.bookServer.authors[0]
                scoreTextView.text=book.bookServer.averageRating.toString()
                favoriteImageView.setOnClickListener{
                    listener.onItemClick(book)
                }
            }
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddBooksAdapter.BooksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_lists,parent,false)
        return AddBooksAdapter.BooksViewHolder(itemView)
    }

    override fun getItemCount(): Int = booksList.size

    override fun onBindViewHolder(holder: AddBooksAdapter.BooksViewHolder, position: Int) {
        val book = booksList[position]
        holder.bindBook(book, listener)
    }

    fun appendItems(newList: ArrayList<BookServer>){
        booksList.clear()
        booksList.addAll(newList as Collection<Item>)
        notifyItemInserted(newList.size)
    }
}