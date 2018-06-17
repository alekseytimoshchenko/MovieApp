package com.example.admin.thebestapp.ui.movieFragment.adapter

import android.support.v7.util.DiffUtil

class RepoComparator<T: DiffInterface>: DiffUtil.ItemCallback<T>()
{
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    {
        return oldItem.getId() == newItem.getId()
    }
    
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean
    {
        return (oldItem.getName().equals(newItem.getName(), ignoreCase = true) //
                && oldItem.getOverview().equals(newItem.getOverview(), ignoreCase = true))
    }
}
