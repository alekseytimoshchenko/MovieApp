package com.example.admin.thebestapp.data.repo

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.example.admin.thebestapp.data.remote.model.MovieObject
import com.example.admin.thebestapp.di.utils.LoadingStatus

data class MovieListQueryResult(
  val data: LiveData<PagedList<MovieObject>>,
  val networkErrors: LiveData<String>,
  val loadingStatus: LiveData<LoadingStatus>
)