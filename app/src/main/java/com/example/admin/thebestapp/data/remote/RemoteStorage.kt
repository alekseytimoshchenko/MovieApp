package com.example.admin.thebestapp.data.remote

import com.example.admin.thebestapp.utils.Constants
import com.example.admin.thebestapp.di.moduls.eRetrofitModules
import com.example.admin.thebestapp.data.remote.model.MovieResponse
import io.reactivex.Flowable
import retrofit2.Retrofit
import javax.inject.Provider

class RemoteStorage(retrofitModules: Map<eRetrofitModules, Provider<Retrofit>>)
{
    private var mMovieEndpoint: IMovieEndpoint = //
            retrofitModules[eRetrofitModules.MOVIE]!!.get().create(IMovieEndpoint::class.java)

    fun getMovies(page: Int): Flowable<MovieResponse> = mMovieEndpoint.getMovie(Constants.API_KEY, page = page)
}