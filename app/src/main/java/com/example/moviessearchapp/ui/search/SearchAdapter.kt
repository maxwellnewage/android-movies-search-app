package com.example.moviessearchapp.ui.search

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearchapp.R
import com.example.moviessearchapp.models.Movie

class SearchVH(v: View): RecyclerView.ViewHolder(v) {
    val tvMovieTitle: TextView = v.findViewById(R.id.tvMovieTitle)
    val tvMovieRate: TextView = v.findViewById(R.id.tvMovieRate)
}

class SearchAdapter(private var movies: List<Movie>): RecyclerView.Adapter<SearchVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
        return SearchVH(LayoutInflater.from(parent.context).inflate(R.layout.item_product_search, parent, false))
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        val movie = movies[position]

        holder.tvMovieTitle.text = movie.title
        holder.tvMovieRate.text = "Rank: ${movie.rank}"
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(products: List<Movie>) {
        this.movies = products
        notifyDataSetChanged()
    }
}