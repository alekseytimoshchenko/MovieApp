package com.example.admin.thebestapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.admin.thebestapp.data.remote.model.MovieObject

@Database(entities = [(MovieObject::class)], version = 1, exportSchema = false)
abstract class Database: RoomDatabase()
{
    abstract fun movieObjDao(): MovieDao
}