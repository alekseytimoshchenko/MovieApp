package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import com.example.admin.thebestapp.base.mvp.MvpPresenter
import com.example.admin.thebestapp.base.mvp.MvpView
import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.PageManager

interface MovieContract
{
    interface View: MvpView
    {
        fun setRecyclerViewConfigurations()
    }
    
    interface Presenter: MvpPresenter<View>
    {
        fun onItemClick()
        
        fun getData(page: Int): PageManager
    }
}
