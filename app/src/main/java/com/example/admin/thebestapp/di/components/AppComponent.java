package com.example.admin.thebestapp.di.components;

import com.example.admin.thebestapp.di.moduls.AppModule;
import com.example.admin.thebestapp.di.scopesqualifiers.AppScope;
import com.example.admin.thebestapp.di.moduls.MovieSubComponentsModule;
import com.example.admin.thebestapp.di.ComponentsHolder;
import com.example.admin.thebestapp.di.moduls.BaseNetworkModule;
import com.example.admin.thebestapp.di.moduls.NetworkMovieModule;

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
