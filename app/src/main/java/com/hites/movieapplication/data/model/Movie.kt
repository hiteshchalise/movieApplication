package com.hites.movieapplication.data.model

import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")var id: Int,
    @SerializedName("adult")var adult: Boolean,
    @SerializedName("backdrop_path")var backdropPath: String,
    @SerializedName("belongs_to_collection") @Ignore var belongsToCollection: Any,
    @SerializedName("budget")var budget: Int,
    @SerializedName("genres")var genres: List<Genre>,
    @SerializedName("homepage")var homepage: String,
    @SerializedName("imdb_id")var imdbId: String,
    @SerializedName("original_language")var originalLanguage: String,
    @SerializedName("original_title")var originalTitle: String,
    @SerializedName("overview")var overview: String,
    @SerializedName("popularity")var popularity: Double,
    @SerializedName("poster_path")var posterPath: String,
    @SerializedName("production_companies")var productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")var productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")var releaseDate: String,
    @SerializedName("revenue")var revenue: Int,
    @SerializedName("runtime")var runtime: Int,
    @SerializedName("spoken_languages")var spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")var status: String,
    @SerializedName("tagline")var tagline: String,
    @SerializedName("title")var title: String,
    @SerializedName("video")var video: Boolean,
    @SerializedName("vote_average")var voteAverage: Double,
    @SerializedName("vote_count")var voteCount: Int
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)


data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val iso_639_1: String,
    val name: String
)
