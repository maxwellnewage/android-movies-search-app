package com.example.moviessearchapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearchapp.R
import java.net.URLEncoder

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val iEmptyList: View = findViewById(R.id.iEmptyList)
        val rvMovies: RecyclerView = findViewById(R.id.rvMovies)
        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.setHasFixedSize(true)

        val pbSearching: ProgressBar = findViewById(R.id.pbSearching)
        val etSearchMovie: EditText = findViewById(R.id.etSearchMovie)

        etSearchMovie.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                if(etSearchMovie.text.length >= 2){
                    iEmptyList.visibility = View.GONE
                    pbSearching.visibility = View.VISIBLE

                    // TODO call viewmodel search
                } else {
                    Toast.makeText(this, "You must to enter more than 2 characters", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }
    }
}