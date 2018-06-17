package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import com.example.admin.thebestapp.base.mvp.MvpPresenter
import com.example.admin.thebestapp.base.mvp.MvpView
import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.PageManager
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieObject

interface MovieContract
{
    interface View: MvpView
    {
        fun setRecyclerViewConfigurations()
    
        fun setMovie(iItem: MovieObject)
    }
    
    interface Presenter: MvpPresenter<View>
    {
        fun onItemClick()
        
        fun getData(page: Int): PageManager
    
        fun setMovie(iItem: MovieObject)
    }
}
