package com.example.admin.thebestapp.di.moduls;

import android.content.Context;

import com.example.admin.thebestapp.di.scopesqualifiers.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule
{
	private final Context context;
	
	public AppModule(Context context)
	{
		this.context = context;
	}
	
	@AppScope
	@Provides
	Context provideContext()
	{
		return context;
	}
}
