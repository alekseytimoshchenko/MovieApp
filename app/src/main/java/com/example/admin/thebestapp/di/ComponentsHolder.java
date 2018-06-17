package com.example.admin.thebestapp.di;

import android.content.Context;

import com.example.admin.thebestapp.di.components.ActivityComponent;
import com.example.admin.thebestapp.di.components.ActivityComponentBuilder;
import com.example.admin.thebestapp.di.components.AppComponent;
import com.example.admin.thebestapp.di.components.DaggerAppComponent;
import com.example.admin.thebestapp.di.moduls.ActivityModule;
import com.example.admin.thebestapp.di.moduls.AppModule;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ComponentsHolder
{
	private final Context context;
	
	@Inject
	Map<Class<?>, Provider<ActivityComponentBuilder>> builders;
	
	private Map<Class<?>, ActivityComponent> components;
	private AppComponent appComponent;
	
	public ComponentsHolder(Context context)
	{
		this.context = context;
	}
	
	public ComponentsHolder init()
	{
		appComponent = DaggerAppComponent.builder().appModule(new AppModule(context)).build();
		appComponent.injectComponentsHolder(this);
		components = new HashMap<>();
		return this;
	}
	
	public AppComponent getAppComponent()
	{
		return appComponent;
	}
	
	public ActivityComponent getActivityComponent(Class<?> cls)
	{
		return getActivityComponent(cls, null);
	}
	
	public ActivityComponent getActivityComponent(Class<?> cls, ActivityModule module)
	{
		ActivityComponent component = components.get(cls);
		
		if (component == null)
		{
			ActivityComponentBuilder builder = builders.get(cls).get();
			
			if (module != null)
			{
				builder.module(module);
			}
			
			component = builder.build();
			components.put(cls, component);
		}
		
		return component;
	}
	
	public void releaseActivityComponent(Class<?> cls)
	{
		components.put(cls, null);
	}
}
