package com.ergea.moviekuapp.data

import com.ergea.moviekuapp.BuildConfig
import com.ergea.moviekuapp.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: ridhogymnastiar
 * Github: https://github.com/ridhogaa
 */


interface ApiService {

    @GET("movie/now_playing")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
    ): MovieResponse

}