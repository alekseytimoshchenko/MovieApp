package com.example.admin.thebestapp.app.dagger;

import com.example.admin.thebestapp.app.ComponentsHolder;
import com.example.admin.thebestapp.app.dagger.network.BaseNetworkModule;
import com.example.admin.thebestapp.ui.movie.movieFragment.dagger.network.NetworkMovieModule;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, //
		BaseNetworkModule.class, //
		NetworkMovieModule.class,//
		MovieSubComponentsModule.class})
public interface AppComponent
{
	void injectComponentsHolder(ComponentsHolder componentsHolder);
}
