package com.example.cubosfilmes.model

import com.google.gson.annotations.SerializedName

class Movies {

    @SerializedName("page")
    var page: Int = 0
        internal set

    @SerializedName("results")
    var movies: List<Movie>? = null
        internal set

    @SerializedName("total_pages")
    var totalPages: Int = 0
        internal set

    @SerializedName("total_results")
    var totalResults: Int = 0
        internal set

}