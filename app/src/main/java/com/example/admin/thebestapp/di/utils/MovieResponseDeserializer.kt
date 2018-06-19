package com.example.admin.thebestapp.di.utils

import com.example.admin.thebestapp.data.remote.model.MovieResponse
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.Type

class MovieResponseDeserializer<T>: JsonDeserializer<MovieResponse>
{
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): MovieResponse
    {
        var runActionResponse = MovieResponse()
        
        Observable.just(json.toString()) //
                .subscribeOn(Schedulers.computation()) //
                .observeOn(AndroidSchedulers.mainThread()) //
                .map {
                    Gson().fromJson(it, MovieResponse::class.java)
                } //
                .blockingSubscribe { //
                    runActionResponse = it //
                } //
        
        return runActionResponse
    }
}