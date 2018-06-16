package com.example.admin.thebestapp.ui.movie.movieFragment.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("page") @Expose var page: Int = 0, //
                         @SerializedName("total_pages") @Expose var totalPages: Int = 0, //
                         @SerializedName("results") @Expose var results: MutableList<MovieObject> = arrayListOf())