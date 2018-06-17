package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class MovieModelFactory(private val mMovieRepository: MovieRepository): ViewModelProvider.NewInstanceFactory()
{
    override fun <T: ViewModel> create(modelClass: Class<T>): T
    {
        return MovieViewModel(mMovieRepository) as T
    }
}
