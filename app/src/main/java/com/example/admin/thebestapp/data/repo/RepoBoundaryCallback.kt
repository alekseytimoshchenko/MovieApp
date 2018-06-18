package com.example.admin.thebestapp.data.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.example.admin.thebestapp.data.local.MovieDao
import com.example.admin.thebestapp.data.remote.IMovieEndpoint
import com.example.admin.thebestapp.data.remote.model.MovieObject
import com.example.admin.thebestapp.di.utils.LoadingStatus
import com.example.admin.thebestapp.utils.Constants.API_KEY
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class RepoBoundaryCallback(//
        private val apiInterface: IMovieEndpoint, //
        private val cache: MovieDao,
        private val mapper: MovieModelMapper
): PagedList.BoundaryCallback<MovieObject>()
{
    // keep the last requested page.
    // When the request is successful, increment the page number.
    private var lastRequestedPage = 1
    
    private val _networkErrors = MutableLiveData<String>()
    
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors
    
    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    
    // LiveData of network errors.
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus
    
    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false
    
    override fun onZeroItemsLoaded()
    {
        Timber.d("onZeroItemsLoaded()")
        requestAndSaveData()
    }
    
    override fun onItemAtEndLoaded(itemAtEnd: MovieObject)
    {
        Timber.d("onItemAtEndLoaded")
        
        requestAndSaveData()
    }
    
    private fun requestAndSaveData()
    {
        if(isRequestInProgress) return
        
        _loadingStatus.postValue(LoadingStatus.LOADING)
        isRequestInProgress = true
    
        apiInterface.getMovie(API_KEY, page = lastRequestedPage) //
                .subscribeOn(Schedulers.io()) //
                .map { mapper.toDbFormatFromApi(it) } //
                .subscribe( //
                        object: DisposableObserver<List<MovieObject>>()
                        {
                            override fun onComplete()
                            {
                                Timber.d("API Request complete")
                                _loadingStatus.postValue(LoadingStatus.SUCCESS)
                            }
                            
                            override fun onNext(movies: List<MovieObject>)
                            {
                                Timber.d("API Request onNext* ${movies.size})")
                                cache.insertAllMovieObjects(movies)
                                lastRequestedPage++
                                isRequestInProgress = false
                            }
                            
                            override fun onError(e: Throwable)
                            {
                                Timber.e(e)
                                _networkErrors.postValue(e.message)
                                _loadingStatus.postValue(LoadingStatus.ERROR)
                                isRequestInProgress = false
                            }
                        } //
                )
    }
}