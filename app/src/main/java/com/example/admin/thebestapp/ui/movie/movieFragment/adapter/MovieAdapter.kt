package com.example.admin.thebestapp.ui.movie.movieFragment.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieObject

class MovieAdapter(diffUtilCallback: DiffUtil.ItemCallback<MovieObject>, private val clickListener: OnItemClick)//
    : PagedListAdapter<MovieObject, MovieHolder>(diffUtilCallback)
{
    interface OnItemClick
    {
        fun itemClick(iItem: MovieObject)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieHolder(view, clickListener)
    }
    
    override fun onBindViewHolder(holder: MovieHolder, position: Int)
    {
        holder.bind(getItem(position))
    }
}
