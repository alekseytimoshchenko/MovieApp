package com.example.admin.thebestapp.ui.movieFragment.adapter

import android.support.v7.util.DiffUtil
import com.example.admin.thebestapp.data.remote.model.MovieObject

class RepoComparator: DiffUtil.ItemCallback<MovieObject>()
{
    override fun areItemsTheSame(oldItem: MovieObject, newItem: MovieObject): Boolean
    {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: MovieObject, newItem: MovieObject): Boolean
    {
        return (oldItem.title.equals(newItem.title, ignoreCase = true) //
                && oldItem.overview_content.equals(newItem.overview_content, ignoreCase = true))
    }
}
