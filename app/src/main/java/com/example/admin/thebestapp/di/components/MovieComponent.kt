package com.example.admin.thebestapp.di.components

import com.example.admin.thebestapp.di.moduls.MovieModule
import com.example.admin.thebestapp.di.scopesqualifiers.MovieScope
import com.example.admin.thebestapp.ui.movieFragment.MovieFragment

import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [(MovieModule::class)])
interface MovieComponent: ActivityComponent<MovieFragment>
{
    @Subcomponent.Builder
    interface Builder: ActivityComponentBuilder<MovieComponent, MovieModule>
}
