package com.example.admin.thebestapp.ui.movieFragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.data.remote.model.MovieObject
import com.example.admin.thebestapp.utils.ui.binding.DataBindingAdapter

class MovieHolder(itemView: View, private val clickListener: (MovieObject) -> Unit): RecyclerView.ViewHolder(itemView)
{
    private val imageView: ImageView = itemView.findViewById(R.id.iv_movie_item_poster)
    
    fun bind(iItem: MovieObject?)
    {
        if(iItem != null)
        {
            DataBindingAdapter.setImageUri(imageView, "https://image.tmdb.org/t/p/h632/${iItem.poster_path}")
            imageView.setOnClickListener { clickListener(iItem) }
        }
        else
        {
            imageView.setImageResource(R.mipmap.ic_launcher)
        }
    }
}
