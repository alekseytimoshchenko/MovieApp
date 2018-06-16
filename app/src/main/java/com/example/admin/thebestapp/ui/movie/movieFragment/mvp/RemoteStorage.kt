package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import com.example.admin.thebestapp.app.Constants
import com.example.admin.thebestapp.app.dagger.network.eRetrofitModules
import com.example.admin.thebestapp.network.IMovieEndpoint
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieResponse
import io.reactivex.Flowable
import retrofit2.Retrofit
import javax.inject.Provider

class RemoteStorage(retrofitModules: Map<eRetrofitModules, Provider<Retrofit>>)
{
    private var mMovieEndpoint: IMovieEndpoint = //
            retrofitModules[eRetrofitModules.MOVIE]!!.get().create(IMovieEndpoint::class.java)

    fun getMovies(page: Int): Flowable<MovieResponse> = mMovieEndpoint.getMovie(Constants.API_KEY, page = page)
}