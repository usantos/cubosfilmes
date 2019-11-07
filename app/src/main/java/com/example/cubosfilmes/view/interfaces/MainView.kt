package com.example.cubosfilmes.view.interfaces

import com.example.cubosfilmes.model.Movie


interface MainView {

    fun showProgress()

    fun hideProgress()

    fun populateRecyclerMovies(items: List<Movie>)

    fun showMessage(message: String)
}
