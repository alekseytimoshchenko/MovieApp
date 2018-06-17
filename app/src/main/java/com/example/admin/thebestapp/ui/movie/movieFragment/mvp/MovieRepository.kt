package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.PageManager

class MovieRepository(private val localStorage: LocalStorage, private val remoteStorage: RemoteStorage)
{
    fun getData(page: Int): PageManager = remoteStorage.getMovies(page) //
            .map { PageManager(it.results, 0, it.totalPages, null, it.page) } //
            .blockingFirst()
}