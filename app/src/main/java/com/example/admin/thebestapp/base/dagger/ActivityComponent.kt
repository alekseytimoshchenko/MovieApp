package com.example.admin.thebestapp.base.dagger

interface ActivityComponent<A>
{
    fun inject(activity: A)
}
