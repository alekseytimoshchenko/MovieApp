package com.example.admin.thebestapp.ui.movieFragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.example.admin.thebestapp.data.remote.model.MovieObject
import com.example.admin.thebestapp.data.repo.MovieListQueryResult
import com.example.admin.thebestapp.data.repo.MovieRepository
import com.example.admin.thebestapp.di.utils.LoadingStatus

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel()
{
    private val queryLiveData = MutableLiveData<String>()
    
    private val movieQueryResults: LiveData<MovieListQueryResult> = Transformations.map(queryLiveData) {
        movieRepository.getData(it)
    }
    
    val movies: LiveData<PagedList<MovieObject>> =
            Transformations.switchMap(movieQueryResults) { it -> it.data }
    
    val networkErrors: LiveData<String> = Transformations.switchMap(
            movieQueryResults) { it -> it.networkErrors }
    
    val loadingStatus: LiveData<LoadingStatus> = Transformations.switchMap(
            movieQueryResults) { it -> it.loadingStatus }
    
    fun loadMovies(query: String)
    {
        if(!query.equals(queryLiveData.value, true))
        {
            queryLiveData.postValue(query)
        }
    }
    
    fun lastQueryValue(): String?
    {
        return queryLiveData.value
    }
}