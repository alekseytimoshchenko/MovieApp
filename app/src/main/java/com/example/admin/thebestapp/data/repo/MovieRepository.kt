package com.example.admin.thebestapp.data.repo

import com.example.admin.thebestapp.ui.movieFragment.adapter.PageManager
import com.example.admin.thebestapp.data.local.LocalStorage
import com.example.admin.thebestapp.data.remote.RemoteStorage

class MovieRepository(private val localStorage: LocalStorage, private val remoteStorage: RemoteStorage)
{
    fun getData(page: Int): PageManager = remoteStorage.getMovies(page) //
            .map { PageManager(it.results, 0, it.totalPages, null, it.page) } //
            .blockingFirst()
}