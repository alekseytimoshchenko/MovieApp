package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
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
    
    private lateinit var movieAdapter: MovieAdapter
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.frag_movie, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        
        App.getApp(context).componentsHolder.getActivityComponent(MovieFragment::class.java, MovieModule()).inject(this)
        presenter.attachView(this)
        presenter.viewIsReady()
        
        val isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }
    
    override fun setRecyclerViewConfigurations()
    {
        val dataSource: DataSource.Factory<Int, MovieObject> = MyPageDataFactory(presenter)
        
        val config: PagedList.Config = PagedList.Config.Builder() //
                .setEnablePlaceholders(true) //
                .setPageSize(10) //
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
        
        movieAdapter = MovieAdapter(MyDiffUtil<MovieObject>())
        
        pagedListLiveData.observe( //
                this, //
                android.arch.lifecycle.Observer {
                    movieAdapter.submitList(it)
                } //
        )
        
        rv_frag_movie_movie_list.adapter = movieAdapter
    }
    
    override fun onDestroy()
    {
        super.onDestroy()
        App.getApp(context).componentsHolder.releaseActivityComponent(MovieFragment::class.java)
    }
}
