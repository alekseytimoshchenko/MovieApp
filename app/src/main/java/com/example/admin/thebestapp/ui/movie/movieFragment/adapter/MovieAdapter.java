package com.example.admin.thebestapp.ui.movie.movieFragment.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.thebestapp.R;
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieObject;

public class MovieAdapter extends PagedListAdapter<MovieObject, MovieHolder>
{
	public MovieAdapter(DiffUtil.ItemCallback<MovieObject> diffUtilCallback)
	{
		super(diffUtilCallback);
	}
	
	@NonNull
	@Override
	public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
		MovieHolder holder = new MovieHolder(view);
		return holder;
	}
	
	@Override
	public void onBindViewHolder(@NonNull MovieHolder holder, int position)
	{
		holder.bind(getItem(position));
	}
}
