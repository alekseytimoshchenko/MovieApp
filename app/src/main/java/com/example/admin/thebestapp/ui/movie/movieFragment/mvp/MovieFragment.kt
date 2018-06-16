package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.app.Constants

class MovieFragment: Fragment(), MovieContract.View
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var rootView = inflater.inflate(R.layout.frag_movie, container, false)
    
        return rootView
    }
    
    override fun setContent()
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
