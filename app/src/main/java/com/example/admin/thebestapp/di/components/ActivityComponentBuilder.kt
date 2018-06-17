package com.example.admin.thebestapp.di.components

import com.example.admin.thebestapp.di.moduls.ActivityModule

interface ActivityComponentBuilder<C: ActivityComponent<*>, M: ActivityModule>
{
    fun build(): C
    fun module(module: M): ActivityComponentBuilder<C, M>
}
