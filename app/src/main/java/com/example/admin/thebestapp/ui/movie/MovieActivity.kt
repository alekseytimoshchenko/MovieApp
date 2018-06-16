package com.example.admin.thebestapp.ui.movie

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.admin.thebestapp.R

class MovieActivity: AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
    
    override fun onConfigurationChanged(newConfig: Configuration?)
    {
        super.onConfigurationChanged(newConfig)
    }
}
