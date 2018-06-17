package com.example.admin.thebestapp.ui.movie

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.admin.thebestapp.R

class MovieActivity: AppCompatActivity()
{
    private var actionBarTitle: String = ""
        set(value)
        {
            supportActionBar?.title = value
        }
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        
        actionBarTitle = getString(R.string.pop_movies)
    }
}
