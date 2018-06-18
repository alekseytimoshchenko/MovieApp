package com.example.admin.thebestapp.data.local

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.admin.thebestapp.data.remote.model.MovieObject

@Dao
interface MovieDao
{
    @Query("SELECT * FROM movieobject WHERE (title LIKE :queryString) OR (id LIKE :queryString) ORDER BY vote_average")
    fun queryMovieObjByTitle(queryString: String): DataSource.Factory<Int, MovieObject>
    
    @Query("SELECT * FROM movieobject WHERE id = :id ")
    fun queryMovieObjById(id: Int): LiveData<MovieObject>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieObject(movieobject: MovieObject)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovieObjects(movieobjects: List<MovieObject>)
}