package com.example.admin.thebestapp.ui.movie.movieFragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public class MyDiffUtil<T extends DiffInterface> extends DiffUtil.ItemCallback<T>
{
	@Override
	public boolean areItemsTheSame(@NonNull final T oldItem, @NonNull final T newItem)
	{
		return oldItem.getId() == newItem.getId();
	}
	
	@Override
	public boolean areContentsTheSame(@NonNull final T oldItem, @NonNull final T newItem)
	{
		return oldItem.getName().equalsIgnoreCase(newItem.getName()) //
				&& oldItem.getOverview().equalsIgnoreCase(newItem.getOverview());
	}
}
