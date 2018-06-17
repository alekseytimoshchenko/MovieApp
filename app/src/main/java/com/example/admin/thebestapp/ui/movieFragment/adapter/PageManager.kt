package com.example.admin.thebestapp.ui.movieFragment.adapter

import com.example.admin.thebestapp.data.remote.model.MovieObject

data class PageManager(val data: MutableList<MovieObject>, //
                       val position: Int, //
                       val totalCount: Int, //
                       val previousPage: Int?, //
                       val nextPage: Int)