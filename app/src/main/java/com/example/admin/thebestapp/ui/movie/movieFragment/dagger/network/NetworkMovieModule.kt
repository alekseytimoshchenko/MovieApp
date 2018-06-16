package com.example.admin.thebestapp.ui.movie.movieFragment.dagger.network

import com.example.admin.thebestapp.app.dagger.network.BaseBuilder
import com.example.admin.thebestapp.app.dagger.network.RetrofitModulesName
import com.example.admin.thebestapp.app.dagger.network.eRetrofitModules
import com.example.admin.thebestapp.ui.movie.movieFragment.network.MovieResponse
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