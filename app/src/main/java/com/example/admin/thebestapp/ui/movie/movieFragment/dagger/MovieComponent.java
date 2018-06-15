package com.example.admin.thebestapp.ui.movie.movieFragment.dagger;

import com.example.admin.thebestapp.base.dagger.ActivityComponent;
import com.example.admin.thebestapp.base.dagger.ActivityComponentBuilder;
import com.example.admin.thebestapp.ui.movie.movieFragment.mvp.MovieFragment;

import dagger.Subcomponent;

@MovieScope
@Subcomponent(modules = MovieModule.class)
public interface MovieComponent extends ActivityComponent<MovieFragment>
{
	@Subcomponent.Builder
	interface Builder extends ActivityComponentBuilder<MovieComponent, MovieModule>
	{
	
	}
}
