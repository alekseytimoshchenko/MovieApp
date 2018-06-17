package com.example.admin.thebestapp.ui.movie.movieFragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.app.GlideApp
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieObject

class MovieHolder(itemView: View, private val clickListener: (MovieObject) -> Unit): RecyclerView.ViewHolder(itemView)
{
    private val imageView: ImageView = itemView.findViewById(R.id.iv_movie_item_poster)
    
    fun bind(iItem: MovieObject?)
    {
        if(iItem != null)
        {
            GlideApp.with(itemView) //
                    .load("https://image.tmdb.org/t/p/w500/${iItem.poster_path}") //
                    .placeholder(R.mipmap.ic_launcher) //
                    .into(imageView)
            
            imageView.setOnClickListener { clickListener(iItem) }
        }
        else
        {
            imageView.setImageResource(R.mipmap.ic_launcher)
        }
    }
}
