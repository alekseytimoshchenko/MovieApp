package com.example.admin.thebestapp.ui.movie.movieFragment.adapter

import android.arch.paging.PageKeyedDataSource
import com.example.admin.thebestapp.ui.movie.movieFragment.mvp.MovieContract
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieObject

class MyPageKeyedDataSource(private val movieStorage: MovieContract.Presenter): PageKeyedDataSource<Int, MovieObject>()
{
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieObject>)
    {
        val result: PageManager = movieStorage.getData(1)
        
        if(params.placeholdersEnabled)
        {
            callback.onResult(result.data, result.position, result.totalCount, result.previousPage, result.nextPage)
        }
        else
        {
            callback.onResult(result.data, result.previousPage, result.nextPage)
        }
    }
    
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieObject>)
    {
        val result = movieStorage.getData(params.key + 1)
        callback.onResult(result.data, result.nextPage)
    }
    
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieObject>)
    {
        val result = movieStorage.getData(params.key - 1)
        callback.onResult(result.data, result.nextPage)
    }
}