package com.example.cubosfilmes.responses

import com.example.cubosfilmes.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MovieListResponse {

    @SerializedName("page")
    @Expose
    var page: Int = 0
        internal set

    @SerializedName("results")
    @Expose
    var movies: List<Movie>? = null
        internal set

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int = 0
        internal set

    @SerializedName("total_results")
    @Expose
    var totalResults: Int = 0
        internal set
}