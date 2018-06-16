package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieResponse
import io.reactivex.Flowable

class MovieModel(private val  localStorage: LocalStorage, private val remoteStorage: RemoteStorage)
{
    fun getMovies(page: Int): Flowable<MovieResponse> = remoteStorage.getMovies(page)
}