package com.example.admin.thebestapp.ui.movie.movieFragment.dagger;

import com.example.admin.thebestapp.base.dagger.ActivityModule;
import com.example.admin.thebestapp.ui.movie.movieFragment.mvp.MovieContract;
import com.example.admin.thebestapp.ui.movie.movieFragment.mvp.MoviePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieModule implements ActivityModule
{
	public MovieModule()
	{
	}
	
	@MovieScope
	@Provides
	MovieContract.Presenter providePinCodePresenter()
	{
		return new MoviePresenter();
	}
}
