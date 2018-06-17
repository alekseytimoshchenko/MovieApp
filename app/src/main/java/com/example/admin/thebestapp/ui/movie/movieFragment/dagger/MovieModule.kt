package com.example.admin.thebestapp.ui.movie.movieFragment.dagger

import android.arch.lifecycle.ViewModelProvider
import com.example.admin.thebestapp.app.dagger.network.eRetrofitModules
import com.example.admin.thebestapp.base.dagger.ActivityModule
import com.example.admin.thebestapp.ui.movie.movieFragment.mvp.*
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
    internal fun getModel(localStorage: LocalStorage, remoteStorage: RemoteStorage): MovieRepository //
            = MovieRepository(localStorage, remoteStorage)
    
    @MovieScope
    @Provides
    internal fun getLocalSotrage(): LocalStorage = LocalStorage()
    
    @MovieScope
    @Provides
    internal fun getRemoteSotrage(retrofitModules: Map<eRetrofitModules, @JvmSuppressWildcards Provider<Retrofit>>): RemoteStorage //
            = RemoteStorage(retrofitModules)
}
