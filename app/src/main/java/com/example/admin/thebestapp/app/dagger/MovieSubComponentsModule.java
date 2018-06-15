package com.example.admin.thebestapp.app.dagger;

import com.example.admin.thebestapp.ui.movie.MovieActivity;
import com.example.admin.thebestapp.base.dagger.ActivityComponentBuilder;
import com.example.admin.thebestapp.ui.movie.movieFragment.dagger.MovieComponent;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MovieComponent.class})
public class MovieSubComponentsModule
{
    @Provides
    @IntoMap
    @ClassKey(MovieActivity.class)
    ActivityComponentBuilder provideSplashViewBuilder(MovieComponent.Builder builder) {
        return builder;
    }
}
