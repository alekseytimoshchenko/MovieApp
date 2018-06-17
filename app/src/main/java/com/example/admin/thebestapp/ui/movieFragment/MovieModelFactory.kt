package com.example.admin.thebestapp.ui.movieFragment

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.admin.thebestapp.data.repo.MovieRepository

class MovieModelFactory(private val mMovieRepository: MovieRepository): ViewModelProvider.NewInstanceFactory()
{
    override fun <T: ViewModel> create(modelClass: Class<T>): T
    {
        return MovieViewModel(mMovieRepository) as T
    }
}
