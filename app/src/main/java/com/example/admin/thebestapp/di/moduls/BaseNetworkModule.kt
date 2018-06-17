package com.example.admin.thebestapp.di.moduls

import android.content.Context
import com.example.admin.follower.network.global.ApiInterceptor
import com.example.admin.thebestapp.BuildConfig
import com.example.admin.thebestapp.di.scopesqualifiers.BaseBuilder
import com.example.admin.thebestapp.utils.Constants
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class BaseNetworkModule
{
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor() //
            .apply {
                HttpLoggingInterceptor.Level.BODY
            }
    
    @Provides
    fun provideApiInterceptor(): ApiInterceptor = ApiInterceptor()
    
    @Provides
    fun provideCache(context: Context): Cache
    {
        val cacheSize: Long = 5 * 1024 * 1024
        val cacheDir: File = context.cacheDir
        return Cache(cacheDir, cacheSize)
    }
    
    @Provides
    fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    
    @Provides
    @BaseBuilder
    fun getBaseBuilder(): Retrofit.Builder
    {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
    }
    
    @Provides
    fun provideBaseHttpClient(loggingInterceptor: HttpLoggingInterceptor, //
                              cache: Cache, //
                              apiInterceptor: ApiInterceptor): OkHttpClient = //
            OkHttpClient.Builder() //
                    .addInterceptor(loggingInterceptor) //
                    .addInterceptor(apiInterceptor) //
                    .connectTimeout(Constants.TIMEOUT_IN_MS, TimeUnit.MILLISECONDS) //
                    .cache(cache) //
                    .build()
}