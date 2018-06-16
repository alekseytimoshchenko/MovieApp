package com.example.admin.thebestapp.ui.movie.movieFragment.network

import com.example.admin.thebestapp.ui.movie.movieFragment.adapter.DiffInterface
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieObject(@SerializedName("vote_count") @Expose var vote_Count: Int = 0, //
                       @SerializedName("id") @Expose var movie_id: Long = 0, //
                       @SerializedName("vote_average") @Expose var vote_average: Double = 0.toDouble(), //
                       @SerializedName("title") @Expose var title: String = "", //
                       @SerializedName("poster_path") @Expose var poster_path: String = "", //
                       @SerializedName("overview") @Expose var overview_content: String = "", //
                       @SerializedName("release_date") @Expose var release_date: String = ""): DiffInterface
{
    override fun getId(): Long
    {
        return movie_id
    }
    
    override fun getName(): String
    {
        return title
    }
    
    override fun getOverview(): String
    {
        return overview_content
    }
}