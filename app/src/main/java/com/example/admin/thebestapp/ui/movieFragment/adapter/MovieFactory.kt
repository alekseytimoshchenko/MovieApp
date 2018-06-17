package com.example.admin.thebestapp.ui.movieFragment.adapter

import android.arch.paging.DataSource
import com.example.admin.thebestapp.data.remote.model.MovieObject
import com.example.admin.thebestapp.data.repo.MovieRepository

class MovieFactory(private val movieRepository: MovieRepository): DataSource.Factory<Int, MovieObject>()
{
    override fun create(): DataSource<Int, MovieObject> = MovieDataSource(movieRepository)
}