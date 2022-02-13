package com.example.moviessearchapp

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.moviessearchapp.commons.MovieUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieMockUnitTest {
    private val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun getJsonMockFromMovies() {
        Assert.assertNotNull(MovieUtils.getJsonMockMoviesFromRaw(appContext))
    }

    @Test
    fun getMovieListFromJson() {
        val jsonMockMoviesFromRaw = MovieUtils.getJsonMockMoviesFromRaw(appContext)
        Assert.assertNotNull(MovieUtils.getListFromJson(jsonMockMoviesFromRaw))
    }

    @Test
    fun searchTermMovieFound() {
        val jsonMockMoviesFromRaw = MovieUtils.getJsonMockMoviesFromRaw(appContext)
        val movieList = MovieUtils.getListFromJson(jsonMockMoviesFromRaw)

        Assert.assertTrue(MovieUtils.getMovieBySearchTitle(movieList, "The Shawshank").isNotEmpty())
    }

    @Test
    fun searchTermMovieFoundUppercase() {
        val jsonMockMoviesFromRaw = MovieUtils.getJsonMockMoviesFromRaw(appContext)
        val movieList = MovieUtils.getListFromJson(jsonMockMoviesFromRaw)

        Assert.assertTrue(MovieUtils.getMovieBySearchTitle(movieList, "THE SHAWSHANK").isNotEmpty())
    }

    @Test
    fun searchTermMovieFoundLowercase() {
        val jsonMockMoviesFromRaw = MovieUtils.getJsonMockMoviesFromRaw(appContext)
        val movieList = MovieUtils.getListFromJson(jsonMockMoviesFromRaw)

        Assert.assertTrue(MovieUtils.getMovieBySearchTitle(movieList, "the shawshank").isNotEmpty())
    }

    @Test
    fun searchTermMovieFoundWithSpaces() {
        val jsonMockMoviesFromRaw = MovieUtils.getJsonMockMoviesFromRaw(appContext)
        val movieList = MovieUtils.getListFromJson(jsonMockMoviesFromRaw)

        Assert.assertTrue(MovieUtils.getMovieBySearchTitle(movieList, "  the shawshank  ").isNotEmpty())
    }

    @Test
    fun searchTermMovieNotFound() {
        val jsonMockMoviesFromRaw = MovieUtils.getJsonMockMoviesFromRaw(appContext)
        val movieList = MovieUtils.getListFromJson(jsonMockMoviesFromRaw)

        Assert.assertFalse(MovieUtils.getMovieBySearchTitle(movieList, "fdsdfdsf").isNotEmpty())
    }

    @Test
    fun searchTermMovieLessTwoChars() {
        val jsonMockMoviesFromRaw = MovieUtils.getJsonMockMoviesFromRaw(appContext)
        val movieList = MovieUtils.getListFromJson(jsonMockMoviesFromRaw)

        Assert.assertFalse(MovieUtils.getMovieBySearchTitle(movieList, "ab").isNotEmpty())
    }

    @Test
    fun searchEmptyTermMovie() {
        val jsonMockMoviesFromRaw = MovieUtils.getJsonMockMoviesFromRaw(appContext)
        val movieList = MovieUtils.getListFromJson(jsonMockMoviesFromRaw)

        Assert.assertFalse(MovieUtils.getMovieBySearchTitle(movieList, "").isNotEmpty())
    }

    @Test
    fun searchNullTermMovie() {
        val jsonMockMoviesFromRaw = MovieUtils.getJsonMockMoviesFromRaw(appContext)
        val movieList = MovieUtils.getListFromJson(jsonMockMoviesFromRaw)

        Assert.assertFalse(MovieUtils.getMovieBySearchTitle(movieList, null).isNotEmpty())
    }
}