package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.app.App
import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.MovieAdapter
import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.MyDiffUtil
import com.example.admin.thebestapp.ui.movie.movieFragment.dagger.MovieModule
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieObject
import kotlinx.android.synthetic.main.frag_movie.*
import javax.inject.Inject

class MovieFragment: Fragment()
{
    @Inject
    lateinit var factory: ViewModelProvider.NewInstanceFactory
    
    private lateinit var viewModel: MovieViewModel
    
    interface OnMovieSelected
    {
        fun setMovie(iItem: MovieObject)
    }
    
    private lateinit var movieSelectedListener: OnMovieSelected
    
    private lateinit var movieAdapter: MovieAdapter
    
    private var isPortrait: Boolean = false
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        movieAdapter = MovieAdapter(MyDiffUtil())
        return inflater.inflate(R.layout.frag_movie, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        
        viewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)
        setToViewModel()
        initializeRecycler()
    }
    
    private fun setToViewModel()
    {
        viewModel.getPagedListLiveData()?.observe( //
                this, //
                android.arch.lifecycle.Observer {
                    movieAdapter.submitList(it)
                } //
        )
    }
    
    override fun onAttach(activity: Activity?)
    {
        super.onAttach(activity)
        
        try
        {
            movieSelectedListener = activity as OnMovieSelected
        }
        catch(e: ClassCastException)
        {
            throw ClassCastException(activity!!.toString() + " must implement OnMovieSelected")
        }
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }
    
    override fun onAttach(context: Context?)
    {
        super.onAttach(context)
        App.instance.componentHolder.getActivityComponent(MovieFragment::class.java, MovieModule()).inject(this)
    }
    
    private fun initializeRecycler()
    {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        rv_frag_movie_movie_list.addItemDecoration(decoration)
    
        if(isPortrait)
        {
            rv_frag_movie_movie_list.layoutManager = GridLayoutManager(context, 2)
        }
        else
        {
            rv_frag_movie_movie_list.layoutManager = GridLayoutManager(context, 3)
        }

        rv_frag_movie_movie_list.adapter = movieAdapter
        
        movieAdapter.onItemClick = { movieObj ->
            setMovie(movieObj)
        }
    }
    
    private fun setMovie(iItem: MovieObject)
    {
        movieSelectedListener.setMovie(iItem)
    }
}
