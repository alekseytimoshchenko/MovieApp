package com.example.admin.thebestapp.data.remote

import com.example.admin.thebestapp.data.remote.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieEndpoint
{
    @GET("movie/popular")
    fun getMovie(@Query("api_key") key: String, //
                 @Query("language") language: String = "en-US", //
                 @Query("page") page: Int): Observable<MovieResponse>
}