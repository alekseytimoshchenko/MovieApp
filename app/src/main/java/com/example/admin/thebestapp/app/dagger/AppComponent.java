package com.example.admin.thebestapp.app.dagger;

import com.example.admin.thebestapp.app.ComponentsHolder;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, MovieSubComponentsModule.class})
public interface AppComponent
{
    void injectComponentsHolder(ComponentsHolder componentsHolder);
}
