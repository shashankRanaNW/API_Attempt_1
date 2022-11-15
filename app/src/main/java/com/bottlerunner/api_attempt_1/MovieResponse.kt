package com.bottlerunner.api_attempt_1

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

class MovieResponse {
    @SerializedName("page")
    var page = 0

    @SerializedName("results")
    var results: List<Movie>? = null

    @SerializedName("total_results")
    var totalResults = 0

    @SerializedName("total_pages")
    var totalPages = 0
}