package com.example.admin.thebestapp.data.repo

import com.example.admin.thebestapp.data.remote.model.MovieObject
import com.example.admin.thebestapp.data.remote.model.MovieResponse
import com.example.admin.thebestapp.utils.Utils

/**
 * Mapping API response to db models
 */
class MovieModelMapper(val utils: Utils)
{
    fun toDbFormatFromApi(movieApiResponse: MovieResponse): List<MovieObject>?
    {
        return movieApiResponse.results //
                .map { //
                    movieModel ->
                    MovieObject( //
                            vote_count = movieModel.vote_count, //
                            movie_id = movieModel.movie_id, //
                            vote_average = movieModel.vote_average, //
                            title = movieModel.title, //
                            poster_path = movieModel.poster_path, //
                            overview_content = movieModel.overview_content, ///
                            release_date = movieModel.release_date)
                }
    }
}