package com.example.admin.thebestapp.di.moduls

import android.arch.lifecycle.ViewModelProvider
import com.example.admin.thebestapp.data.local.MovieDao
import com.example.admin.thebestapp.data.remote.IMovieEndpoint
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
    internal fun getModel(apiInterface: IMovieEndpoint, movieDao: MovieDao, mapper: MovieModelMapper): MovieRepository //
            = MovieRepository(apiInterface, movieDao, mapper)
    
    @MovieScope
    @Provides
    internal fun getMapper(utils: Utils): MovieModelMapper = MovieModelMapper(utils)
    
    @MovieScope
    @Provides
    internal fun getMovieEndpoint(retrofitModules: Map<eRetrofitModules, @JvmSuppressWildcards Provider<Retrofit>>): IMovieEndpoint //
            = retrofitModules[eRetrofitModules.MOVIE]!!.get().create(IMovieEndpoint::class.java)
}
