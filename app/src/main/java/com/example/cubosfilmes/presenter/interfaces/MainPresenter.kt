package com.example.cubosfilmes.presenter.interfaces

interface MainPresenter {

    fun onResume()

    fun onItemClicked(position: Int)

    fun onDestroy()

    fun onItemLongClick(position: Int)

    fun searchMovie(query: String)

    fun fetchPopularMovies(page: Int)
}
