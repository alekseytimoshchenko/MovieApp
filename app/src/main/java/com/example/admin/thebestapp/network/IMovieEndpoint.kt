package com.example.admin.thebestapp.network

import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieEndpoint
{
    //    @GET("movie/popular?api_key=7591f728356045226bc3262c1c70d14b&language=en-US&page=2")
    @GET("movie/popular")
    fun getMovie(@Query("api_key") key: String, //
                 @Query("language") language: String = "en-US", //
                 @Query("page") page: Int): Flowable<MovieResponse>
}