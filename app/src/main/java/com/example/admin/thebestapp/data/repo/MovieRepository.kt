package com.example.admin.thebestapp.data.repo

import android.arch.paging.LivePagedListBuilder
import com.example.admin.thebestapp.data.local.LocalStorage
import com.example.admin.thebestapp.data.local.MovieDao
import com.example.admin.thebestapp.data.remote.RemoteStorage
import com.example.admin.thebestapp.utils.Constants.DATABASE_PAGE_SIZE
import timber.log.Timber

class MovieRepository (private val localStorage: LocalStorage, //
                       private val remoteStorage: RemoteStorage,//
                      private val movieDao: MovieDao
)
{
    //    fun getData(page: Int): PageManager = remoteStorage.getMovies(page) //
    //            .map { PageManager(it.results, 0, it.totalPages, null, it.page) } //
    //            .blockingFirst()
    
    fun getData(query: String): MovieListQueryResult
    {
        Timber.d("New Query: Get ($query)")
        // Get data source factory from the local cache
        val dataSourceFactory = movieDao.queryMovieObjByTitle(query.toQueryPattern())
    
        // Construct the boundary callback
        val boundaryCallback = RepoBoundaryCallback(remoteStorage.mMovieEndpoint, movieDao)
        val networkErrors = boundaryCallback.networkErrors
        val loadingStatus = boundaryCallback.loadingStatus
    
        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()
    
        // Get the network errors exposed by the boundary callback
        return MovieListQueryResult(data, networkErrors, loadingStatus)
    }
    
    private fun String.toQueryPattern(): String {
        // appending '%' so we can allow other characters to be before and after the query string
        return "%${this.replace(' ', '%')}%"
    }
}