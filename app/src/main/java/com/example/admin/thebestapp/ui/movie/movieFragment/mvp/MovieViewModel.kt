package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.MyPageDataFactory
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieObject
import java.util.concurrent.Executors

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel()
{
    var data: LiveData<PagedList<MovieObject>>? = null
    
    fun getPagedListLiveData(): LiveData<PagedList<MovieObject>>?
    {
        return if(data == null)
        {
            createPageData()
        }
        else
        {
            data
        }
    }
    
    fun createPageData(): LiveData<PagedList<MovieObject>>
    {
        val dataSource: DataSource.Factory<Int, MovieObject> = MyPageDataFactory(movieRepository)
        
        val config: PagedList.Config = PagedList.Config.Builder() //
                .setEnablePlaceholders(true) //
                .setPageSize(20) //
                .build()
        
        val pagedListLiveData: LiveData<PagedList<MovieObject>> = //
                LivePagedListBuilder(dataSource, config) //
                        .setFetchExecutor(Executors.newSingleThreadExecutor()) //
                        .setBoundaryCallback( //
                                object: PagedList.BoundaryCallback<MovieObject>()
                                {
                                    override fun onItemAtEndLoaded(itemAtEnd: MovieObject)
                                    {
                                        super.onItemAtEndLoaded(itemAtEnd)
                                    }
                                    
                                    override fun onItemAtFrontLoaded(itemAtFront: MovieObject)
                                    {
                                        super.onItemAtFrontLoaded(itemAtFront)
                                    }
                                    
                                    override fun onZeroItemsLoaded()
                                    {
                                        super.onZeroItemsLoaded()
                                    }
                                } //
                        ).build()
        
        return pagedListLiveData
    }
}