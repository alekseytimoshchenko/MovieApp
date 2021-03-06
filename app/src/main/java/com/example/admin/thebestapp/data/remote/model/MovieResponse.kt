package com.example.admin.thebestapp.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("page") @Expose var page: Int = 0, //
                         @SerializedName("total_pages") @Expose var totalPages: Int = 0, //
                         @SerializedName("results") @Expose var results: List<MovieResponseObj> = arrayListOf())