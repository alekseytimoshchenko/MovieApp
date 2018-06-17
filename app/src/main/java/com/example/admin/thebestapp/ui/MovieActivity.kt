package com.example.admin.thebestapp.ui

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.utils.Constants
import com.example.admin.thebestapp.ui.descriptionFragment.DescriptionFragment
import com.example.admin.thebestapp.ui.movieFragment.MovieFragment
import com.example.admin.thebestapp.data.remote.model.MovieObject

class MovieActivity: AppCompatActivity(), MovieFragment.OnMovieSelected
{
    private var actionBarTitle: String = ""
        set(value)
        {
            supportActionBar?.title = value
        }
    
    private var isPortrait: Boolean = false
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        
        actionBarTitle = getString(R.string.pop_movies)
        
        setRootFragment()
    }
    
    private fun setRootFragment()
    {
        if(isPortrait)
        {
            val newFragment = MovieFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_activity_movie_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
    
    override fun setMovie(iItem: MovieObject)
    {
        val articleFrag = supportFragmentManager.findFragmentById(R.id.frag_description_frag) as DescriptionFragment?
        
        if(articleFrag != null)
        {
            articleFrag.setMovie(iItem)
        }
        else
        {
            val newFragment = DescriptionFragment()
            val args = Bundle()
            args.putParcelable(Constants.MOVIE_OBJ, iItem)
            newFragment.arguments = args
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_activity_movie_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
