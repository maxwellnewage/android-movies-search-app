package com.example.moviessearchapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearchapp.R
import com.example.moviessearchapp.ui.search.SearchAdapter
import com.example.moviessearchapp.ui.search.SearchViewModel

class HomeActivity : AppCompatActivity(), SearchViewModel.OnSearchResponse {
    private lateinit var viewModel: SearchViewModel
    private val tvMovieResult: TextView by lazy { findViewById(R.id.tvMovieResult) }
    private val tvMovieInstructions: TextView by lazy { findViewById(R.id.tvMovieInstructions) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.setOnSearchResponse(this)

        val iEmptyList: View = findViewById(R.id.iEmptyList)
        val rvMovies: RecyclerView = findViewById(R.id.rvMovies)
        val adapter = SearchAdapter(emptyList())
        rvMovies.adapter = adapter
        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.setHasFixedSize(true)

        val pbSearching: ProgressBar = findViewById(R.id.pbSearching)
        val etSearchMovie: EditText = findViewById(R.id.etSearchMovie)

        etSearchMovie.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                if(etSearchMovie.text.length >= 2){
                    iEmptyList.visibility = View.GONE
                    pbSearching.visibility = View.VISIBLE

                    viewModel.searchMovie(etSearchMovie.text.toString())
                } else {
                    Toast.makeText(this, "You must to enter more than 2 characters", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }

        viewModel.movies.observe(this) {
            adapter.setData(it)

            if (it.isEmpty()) {
                iEmptyList.visibility = View.VISIBLE
                rvMovies.visibility = View.GONE
            } else {
                iEmptyList.visibility = View.GONE
                rvMovies.visibility = View.VISIBLE
            }

            pbSearching.visibility = View.GONE
        }
    }

    override fun onSearchError(message: String?) {
        Log.d(localClassName, "Error: $message")
        tvMovieResult.text = message
        tvMovieInstructions.visibility = View.GONE
    }
}