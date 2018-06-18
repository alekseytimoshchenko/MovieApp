package com.example.admin.thebestapp.data.remote

import com.example.admin.thebestapp.di.moduls.eRetrofitModules
import retrofit2.Retrofit
import javax.inject.Provider

class RemoteStorage (retrofitModules: Map<eRetrofitModules, Provider<Retrofit>>)
{
    var mMovieEndpoint: IMovieEndpoint = //
            retrofitModules[eRetrofitModules.MOVIE]!!.get().create(IMovieEndpoint::class.java)
        private set
}