package com.example.admin.thebestapp.di.moduls;

import com.example.admin.thebestapp.di.components.ActivityComponentBuilder;
import com.example.admin.thebestapp.di.components.MovieComponent;
import com.example.admin.thebestapp.ui.movieFragment.MovieFragment;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MovieComponent.class})
public class MovieSubComponentsModule
{
    @Provides
    @IntoMap
    @ClassKey(MovieFragment.class)
    ActivityComponentBuilder provideSplashViewBuilder(MovieComponent.Builder builder) {
        return builder;
    }
}
