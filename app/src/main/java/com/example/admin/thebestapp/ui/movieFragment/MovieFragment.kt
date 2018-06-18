package com.example.admin.thebestapp.ui.movieFragment

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.thebestapp.App
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.data.remote.model.MovieObject
import com.example.admin.thebestapp.di.moduls.MovieModule
import com.example.admin.thebestapp.di.utils.LoadingStatus
import com.example.admin.thebestapp.ui.movieFragment.adapter.MovieAdapter
import kotlinx.android.synthetic.main.frag_movie.*
import org.jetbrains.anko.support.v4.toast
import timber.log.Timber
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
        movieAdapter = MovieAdapter()
        return inflater.inflate(R.layout.frag_movie, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        
        viewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)
        subscribeToViewModel()
        initializeRecycler()
        viewModel.loadMovies(viewModel.lastQueryValue() ?: "")
    }
    
    private fun subscribeToViewModel()
    {
        viewModel.movies.observe(this, Observer<PagedList<MovieObject>> {
            Timber.d("viewModel- list: ${it?.size}")
            showEmptyState(it?.size == 0)
            movieAdapter.submitList(it)
        })
    
        viewModel.networkErrors.observe(this, Observer<String> {
            toast("\uD83D\uDE28 Wooops $it")
        })
    
        viewModel.loadingStatus.observe(this, Observer { result ->
            Timber.d("loadingStatus.observe changed - $result")
            when(result)
            {
                LoadingStatus.SUCCESS, LoadingStatus.ERROR -> pb_frag_movie_loading.visibility = View.GONE
                LoadingStatus.LOADING -> pb_frag_movie_loading.visibility = View.VISIBLE
                null -> pb_frag_movie_loading.visibility = View.GONE
            }
        })
    }
    
    private fun showEmptyState(isShowEmptyState: Boolean)
    {
        if(isShowEmptyState)
        {
            rv_frag_movie_movie_list.visibility = View.GONE
            tvEmptyState.visibility = View.VISIBLE
        }
        else
        {
            rv_frag_movie_movie_list.visibility = View.VISIBLE
            tvEmptyState.visibility = View.GONE
        }
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
