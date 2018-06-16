package com.example.admin.thebestapp.ui.movie.movieFragment.mvp

import com.example.admin.thebestapp.base.mvp.PresenterBase
import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.PageManager

class MoviePresenter (private val model: MovieModel): PresenterBase<MovieContract.View>(), MovieContract.Presenter
{
    override fun viewIsReady()
    {
        view?.setRecyclerViewConfigurations()
    }
    
    override fun onItemClick()
    {
    }
    
    override fun getData(page: Int): PageManager
    {
        return model.getMovies(page)//
                .map { PageManager(it.results, 0, it.totalPages, null, it.page) }//
                .blockingFirst()
    }
}
