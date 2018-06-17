package com.example.admin.thebestapp.ui.movieFragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.admin.thebestapp.data.repo.MovieRepository
import com.example.admin.thebestapp.ui.movieFragment.adapter.MovieFactory
import com.example.admin.thebestapp.data.remote.model.MovieObject
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
        val dataSource: DataSource.Factory<Int, MovieObject> = MovieFactory(movieRepository)
        
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