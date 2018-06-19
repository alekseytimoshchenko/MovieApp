package com.example.admin.thebestapp.ui

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.data.remote.model.MovieObject
import com.example.admin.thebestapp.ui.descriptionFragment.DescriptionFragment
import com.example.admin.thebestapp.ui.movieFragment.MovieFragment

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
        if(!isPortrait && supportFragmentManager.backStackEntryCount > 0)
        {
            supportFragmentManager.popBackStack()
            supportFragmentManager.executePendingTransactions()
        }
        
        if(supportFragmentManager.findFragmentByTag(MovieFragment.frag_tag) == null)
        {
            supportFragmentManager.beginTransaction().replace( //
                    R.id.fl_activity_movie_container, //
                    MovieFragment.newInstance(), //
                    MovieFragment.frag_tag) //
                    .commit()
        }
        
        if(!isPortrait && supportFragmentManager.findFragmentByTag(DescriptionFragment.frag_tag) == null)
        {
            supportFragmentManager.beginTransaction().replace( //
                    R.id.fl_activity_description_container, //
                    //                    DescriptionFragment.newInstance(), //
                    DescriptionFragment(), //
                    DescriptionFragment.frag_tag) //
                    .commit()
        }
    }
    
    override fun setMovie(iItem: MovieObject)
    {
        val articleFrag = supportFragmentManager.findFragmentByTag(DescriptionFragment.frag_tag) as DescriptionFragment?
        
        if(isPortrait && articleFrag != null)
        {
            supportFragmentManager.beginTransaction().remove(articleFrag).commit()
            supportFragmentManager.executePendingTransactions()
        }
        
        if(articleFrag != null)
        {
            if(isPortrait)
            {
                val frag = DescriptionFragment()
                
                supportFragmentManager.beginTransaction().replace( //
                        R.id.fl_activity_movie_container, //
                        frag, //
                        DescriptionFragment.frag_tag) //
                        .addToBackStack(DescriptionFragment.frag_tag) //
                        .commit()
                
                supportFragmentManager.executePendingTransactions()
                
                frag.setMovie(iItem)
            }
            else
            {
                articleFrag.setMovie(iItem)
            }
        }
        else
        {
            supportFragmentManager.beginTransaction().replace( //
                    R.id.fl_activity_movie_container, //
                    DescriptionFragment.newInstance(iItem), //
                    DescriptionFragment.frag_tag) //
                    .addToBackStack(DescriptionFragment.frag_tag) //
                    .commit()
        }
    }
}
