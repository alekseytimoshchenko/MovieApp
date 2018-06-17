package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import android.app.Activity
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.app.App
import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.MovieAdapter
import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.MyDiffUtil
import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.MyPageDataFactory
import com.example.admin.thebestapp.ui.movie.movieFragment.dagger.MovieModule
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieObject
import kotlinx.android.synthetic.main.frag_movie.*
import java.util.concurrent.Executors
import javax.inject.Inject



class MovieFragment: Fragment(), MovieContract.View
{
    @Inject
    lateinit var presenter: MovieContract.Presenter
    
    interface OnMovieSelected
    {
        fun setMovie(iItem: MovieObject)
    }
    
    private lateinit var movieSelectedListener: OnMovieSelected
    
    private lateinit var movieAdapter: MovieAdapter
    
    private var isPortrait: Boolean = false
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.frag_movie, container, false)
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
        
        App.getApp(context).componentsHolder.getActivityComponent(MovieFragment::class.java, MovieModule()).inject(this)
        presenter.attachView(this)
        presenter.viewIsReady()
    }
    
    override fun setRecyclerViewConfigurations()
    {
        val dataSource: DataSource.Factory<Int, MovieObject> = MyPageDataFactory(presenter)
        
        val config: PagedList.Config = PagedList.Config.Builder() //
                .setEnablePlaceholders(true) //
                .setPageSize(20) //
                .build()
        
        val pagedListLiveData = //
                LivePagedListBuilder(dataSource, config) //
                        .setFetchExecutor(Executors.newSingleThreadExecutor()) //
                        .setBoundaryCallback(object: PagedList.BoundaryCallback<MovieObject>()
                        {
                            override fun onItemAtEndLoaded(itemAtEnd: MovieObject)
                            {
                                super.onItemAtEndLoaded(itemAtEnd)
                                Toast.makeText(context, "Last element", Toast.LENGTH_SHORT).show()
                            }
                            
                            override fun onItemAtFrontLoaded(itemAtFront: MovieObject)
                            {
                                super.onItemAtFrontLoaded(itemAtFront)
                                Toast.makeText(context, "First element", Toast.LENGTH_SHORT).show()
                            }
                            
                            override fun onZeroItemsLoaded()
                            {
                                super.onZeroItemsLoaded()
                                Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show()
                            }
                        }).build()
        
        movieAdapter = MovieAdapter(MyDiffUtil(), itemClick)
        
        pagedListLiveData.observe( //
                this, //
                android.arch.lifecycle.Observer {
                    movieAdapter.submitList(it)
                } //
        )
        
        if(isPortrait)
        {
            rv_frag_movie_movie_list.layoutManager = GridLayoutManager(context, 2)
        }
        else
        {
            rv_frag_movie_movie_list.layoutManager = GridLayoutManager(context, 3)
        }
        
        rv_frag_movie_movie_list.adapter = movieAdapter
    }
    
    private val itemClick = object: MovieAdapter.OnItemClick
    {
        override fun itemClick(iItem: MovieObject)
        {
            presenter.setMovie(iItem)
        }
    }
    
    override fun setMovie(iItem: MovieObject)
    {
        movieSelectedListener.setMovie(iItem)
    }
    
    override fun onDestroy()
    {
        super.onDestroy()
        App.getApp(context).componentsHolder.releaseActivityComponent(MovieFragment::class.java)
    }
}
