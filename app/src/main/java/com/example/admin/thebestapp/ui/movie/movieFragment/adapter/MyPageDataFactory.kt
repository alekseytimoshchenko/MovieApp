package com.example.admin.thebestapp.ui.movie.movieFragment.adapter

import android.arch.paging.DataSource
import com.example.admin.thebestapp.ui.movie.movieFragment.mvp.MovieRepository
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieObject

class MyPageDataFactory(private val movieRepository: MovieRepository): DataSource.Factory<Int, MovieObject>()
{
    override fun create(): DataSource<Int, MovieObject> = MyPageKeyedDataSource(movieRepository)
}