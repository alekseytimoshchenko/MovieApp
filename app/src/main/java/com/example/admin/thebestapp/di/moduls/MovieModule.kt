package com.example.admin.thebestapp.di.moduls

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.admin.thebestapp.data.local.LocalStorage
import com.example.admin.thebestapp.data.local.MovieDao
import com.example.admin.thebestapp.data.remote.RemoteStorage
import com.example.admin.thebestapp.data.repo.MovieModelMapper
import com.example.admin.thebestapp.data.repo.MovieRepository
import com.example.admin.thebestapp.di.scopesqualifiers.MovieScope
import com.example.admin.thebestapp.ui.movieFragment.MovieModelFactory
import com.example.admin.thebestapp.utils.Utils
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Provider

@Module
class MovieModule: ActivityModule
{
    @MovieScope
    @Provides
    internal fun getMovieModelFactory(iRepository: MovieRepository): ViewModelProvider.NewInstanceFactory //
            = MovieModelFactory(iRepository)
    
    @MovieScope
    @Provides
    internal fun getModel(localStorage: LocalStorage, remoteStorage: RemoteStorage, movieDao: MovieDao, mapper: MovieModelMapper): MovieRepository //
            = MovieRepository(localStorage, remoteStorage, movieDao, mapper)
    
    @MovieScope
    @Provides
    internal fun getLocalSotrage(): LocalStorage = LocalStorage()
    
    @MovieScope
    @Provides
    internal fun getMapper(utils: Utils): MovieModelMapper = MovieModelMapper(utils)
    
    @MovieScope
    @Provides
    internal fun getRemoteSotrage(retrofitModules: Map<eRetrofitModules, @JvmSuppressWildcards Provider<Retrofit>>): RemoteStorage //
            = RemoteStorage(retrofitModules)
}
