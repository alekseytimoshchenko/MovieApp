package com.example.admin.thebestapp.di.moduls

import android.arch.persistence.room.Room
import android.content.Context
import com.example.admin.thebestapp.data.local.Database
import com.example.admin.thebestapp.data.local.MovieDao
import com.example.admin.thebestapp.di.scopesqualifiers.AppScope
import com.example.admin.thebestapp.utils.Constants.DB_NAME
import com.example.admin.thebestapp.utils.Utils
import dagger.Module
import dagger.Provides

@Module
class DbModule
{
    @Provides
    @AppScope
    fun provideMovieDatabase(context: Context): Database //
            = Room.databaseBuilder(context, Database::class.java, DB_NAME).fallbackToDestructiveMigration().build()
    
    @Provides
    @AppScope
    fun movieDao(database: Database): MovieDao = database.movieObjDao()
    
    @Provides
    @AppScope
    fun provideUtils(context: Context): Utils = Utils(context)
}