package com.example.admin.thebestapp.di.scopesqualifiers

import com.example.admin.thebestapp.di.moduls.eRetrofitModules
import dagger.MapKey

@MapKey
annotation class RetrofitModulesName(val value: eRetrofitModules)