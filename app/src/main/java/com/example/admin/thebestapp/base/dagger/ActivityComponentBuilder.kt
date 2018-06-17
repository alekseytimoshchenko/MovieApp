package com.example.admin.thebestapp.base.dagger

interface ActivityComponentBuilder<C: ActivityComponent<*>, M: ActivityModule>
{
    fun build(): C
    fun module(module: M): ActivityComponentBuilder<C, M>
}
