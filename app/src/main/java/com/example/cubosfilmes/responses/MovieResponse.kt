package com.example.cubosfilmes.responses

import com.example.cubosfilmes.model.Movie
import com.google.gson.annotations.SerializedName

class MovieResponse : Movie() {

    @SerializedName("genres")
    internal var genres: List<Genre>? = null

    override var genreIds: IntArray
        get() {
            val ids = IntArray(genres!!.size)
            for (i in genres!!.indices) {
                ids[i] = genres!![i].id
            }
            return ids
        }
        set(value: IntArray) {
            super.genreIds = value
        }

    /**
     * Extracts the movie from the response (discarding this wrapper type).
     * Sets any fields on the base type (ie, genre_ids).
     */
    val movie: Movie
        get() {
            this.genreIds = genreIds
            return this
        }

    internal class Genre {
        var id: Int = 0
        var name: String? = null
    }
}