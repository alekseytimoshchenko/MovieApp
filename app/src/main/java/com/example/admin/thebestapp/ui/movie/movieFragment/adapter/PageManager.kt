package com.example.admin.thebestapp.ui.movie.movieFragment.adapter

import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieObject

data class PageManager(val data: MutableList<MovieObject>, //
                       val position: Int, //
                       val totalCount: Int, //
                       val previousPage: Int, //
                       val nextPage: Int)