package com.example.admin.thebestapp.di.moduls

import com.example.admin.thebestapp.di.scopesqualifiers.BaseBuilder
import com.example.admin.thebestapp.di.scopesqualifiers.RetrofitModulesName
import com.example.admin.thebestapp.data.remote.model.MovieResponse
import com.example.admin.thebestapp.di.utils.MovieResponseDeserializer
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkMovieModule
{
    private val runActionResponseDeserializer = GsonBuilder() //
            .registerTypeAdapter(MovieResponse::class.java, MovieResponseDeserializer<MovieResponse>()) //
            .create()!!
    
    @Provides
    @IntoMap
    @RetrofitModulesName(eRetrofitModules.MOVIE)
    fun retrofitRunActivity(okHttpClient: OkHttpClient, //
                            rxAdaptor: RxJava2CallAdapterFactory, //
                            @BaseBuilder iBuilder: Retrofit.Builder): Retrofit = //
            iBuilder.addConverterFactory(GsonConverterFactory.create(runActionResponseDeserializer)) //mapper
                    .addCallAdapterFactory(rxAdaptor) //
                    .client(okHttpClient) //
                    .build()
    
}