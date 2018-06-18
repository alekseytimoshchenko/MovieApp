package com.example.admin.thebestapp.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponseObj( //
        @SerializedName("vote_count") @Expose var vote_count: Int = 0, //
        @SerializedName("id") @Expose var movie_id: Long = 0, //
        @SerializedName("vote_average") var vote_average: Double = 0.toDouble(), //
        @SerializedName("title") @Expose var title: String = "", //
        @SerializedName("poster_path") @Expose var poster_path: String = "", //
        @SerializedName("overview") @Expose var overview_content: String = "", //
        @SerializedName("release_date") @Expose var release_date: String = "")
