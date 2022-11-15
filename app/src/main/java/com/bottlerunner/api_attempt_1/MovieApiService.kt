package com.bottlerunner.api_attempt_1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey : String) : Call<MovieResponse>

}