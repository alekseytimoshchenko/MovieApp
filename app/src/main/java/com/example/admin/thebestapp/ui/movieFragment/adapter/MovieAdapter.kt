package com.example.admin.thebestapp.ui.movieFragment.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.data.remote.model.MovieObject

class MovieAdapter: PagedListAdapter<MovieObject, MovieHolder>(REPO_COMPARATOR)
{
    var onItemClick: (MovieObject) -> Unit = {}
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieHolder(view, onItemClick)
    }
    
    override fun onBindViewHolder(holder: MovieHolder, position: Int)
    {
        holder.bind(getItem(position))
    }
    
    companion object
    {
        private val REPO_COMPARATOR = object: DiffUtil.ItemCallback<MovieObject>()
        {
            override fun areItemsTheSame(oldItem: MovieObject, newItem: MovieObject): Boolean = oldItem.id == newItem.id
            
            override fun areContentsTheSame(oldItem: MovieObject, newItem: MovieObject): Boolean = oldItem == newItem
        }
    }
}
