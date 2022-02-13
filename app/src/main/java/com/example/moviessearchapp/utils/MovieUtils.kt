package com.example.moviessearchapp.utils

import android.content.Context
import com.example.moviessearchapp.R
import com.example.moviessearchapp.models.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type

class MovieUtils {
    companion object {
        private val listMovieType: Type = object : TypeToken<List<Movie>>() {}.type

        fun getJsonMockMoviesFromRaw(context: Context): String? {
            val jsonString: String
            try {
                jsonString = context.resources.openRawResource(R.raw.movies).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun getListFromJson(json: String?): List<Movie>? {
            return Gson().fromJson(json, listMovieType)
        }

        fun getMovieBySearchTitle(moveList: List<Movie>?, title: String?): List<Movie> {
            if(moveList.isNullOrEmpty() || title.isNullOrEmpty() || title.length <= 2) return emptyList()

            return moveList.filter { movie -> movie.title.contains(title.trim(), true) }
        }
    }
}