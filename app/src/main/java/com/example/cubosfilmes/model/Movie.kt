package com.example.cubosfilmes.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

open class Movie : Serializable {

    @SerializedName("id")
    var id: Int = 0
        internal set

    @SerializedName("adult")
    var isAdult: Boolean = false
        internal set

    @SerializedName("backdrop_path")
    var backdropPath: String? = null
        internal set

    @SerializedName("genre_ids")
    open var genreIds = IntArray(0)

    /*public List<String> getMovieGenres() {
        List<String> genres = new ArrayList<>();
        int[] ids = getGenreIds();
        for (int i=0; i<ids.length; i++) {
            MovieGenre genre = MovieGenre.getById(ids[i]);
            if (genre != null) {
                genres.add(genre.getTitle());
            }
        }
        return genres;
    }*/

    @SerializedName("original_language")
    var originalLanguage: String? = null
        internal set

    @SerializedName("original_title")
    var originalTitle: String? = null
        internal set

    @SerializedName("overview")
    var overview: String? = null
        internal set

    @SerializedName("release_date")
    var releaseDate: String? = null
        internal set

    @SerializedName("poster_path")
    var posterPath: String? = null
        internal set

    @SerializedName("popularity")
    var popularity: Float = 0.toFloat()
        internal set

    @SerializedName("title")
    var title: String? = null
        internal set

    @SerializedName("video")
    var isVideo: Boolean = false
        internal set

    @SerializedName("vote_average")
    var voteAverage: Float = 0.toFloat()
        internal set

    @SerializedName("vote_count")
    var voteCount: Int = 0
        internal set

    val backdropUrl: String
        get() = BASE_IMG_URL + BACKDROP_SIZE_W300 + backdropPath

    val posterUrl: String
        get() = BASE_IMG_URL + POSTER_SIZE_W185 + posterPath

    fun getBackdropUrl(screenWidth: Int): String {
        return if (screenWidth >= 1024) {
            BASE_IMG_URL + BACKDROP_SIZE_W780 + backdropPath
        } else BASE_IMG_URL + BACKDROP_SIZE_W300 + backdropPath
    }

    fun getPosterUrl(screenWidth: Int): String {
        return if (screenWidth >= 1024) {
            BASE_IMG_URL + POSTER_SIZE_W342 + posterPath
        } else BASE_IMG_URL + POSTER_SIZE_W185 + posterPath
    }

    companion object {

        internal val BASE_IMG_URL = "http://image.tmdb.org/t/p/"

        // Poster image sizes
        internal val POSTER_SIZE_W92 = "w92"
        internal val POSTER_SIZE_W154 = "w154"
        internal val POSTER_SIZE_W185 = "w185"
        internal val POSTER_SIZE_W342 = "w342"
        internal val POSTER_SIZE_W500 = "w500"
        internal val POSTER_W780 = "w780"
        internal val POSTER_SIZE_ORIGINAL = "original"

        // Backdrop image sizes
        internal val BACKDROP_SIZE_W300 = "w300"
        internal val BACKDROP_SIZE_W780 = "w780"
        internal val BACKDROP_SIZE_W1280 = "w1280"
        internal val BACKDROP_SIZE_ORIGINAL = "original"

        // recommended for most phones:
        internal val SIZE_DEFAULT = POSTER_SIZE_W185
    }
}
