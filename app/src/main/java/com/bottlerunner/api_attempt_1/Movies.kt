package com.bottlerunner.api_attempt_1

import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("adult") var isAdult: Boolean,
    @SerializedName("overview") var overview: String,
    @SerializedName("release_date") var releaseDate: String,
    var genreIds: List<Int>,
    var id: Int,
    var originalTitle: String,
    var originalLanguage: String,
    var title: String,
    var backdropPath: String,
    var popularity: Double,
    var voteCount: Int,
    var video: Boolean,
    var voteAverage: Double
)