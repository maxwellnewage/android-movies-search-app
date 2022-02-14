package com.example.moviessearchapp.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.moviessearchapp.commons.MovieUtils
import com.example.moviessearchapp.models.Movie

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    val movies = MutableLiveData<List<Movie>>()
    private var onSearchResponse: OnSearchResponse? = null

    fun searchMovie(term: String) {
        val jsonMockMoviesFromRaw = MovieUtils.getJsonMockMoviesFromRaw(getApplication())
        val movieList = MovieUtils.getListFromJson(jsonMockMoviesFromRaw)

        val moviesResult = MovieUtils.getMovieBySearchTitle(movieList, term)

        // this is a simulation for an async call
        if(moviesResult.isNotEmpty()) {
            movies.postValue(moviesResult)
        } else {
            movies.postValue(moviesResult)
            onSearchResponse?.onSearchError("We don't found the movie you are looking for...")
        }
    }

    fun setOnSearchResponse(onSearch: OnSearchResponse) {
        this.onSearchResponse = onSearch
    }

    interface OnSearchResponse {
        fun onSearchError(message: String?)
    }
}