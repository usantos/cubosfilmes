package com.example.cubosfilmes.presenter

import com.example.cubosfilmes.interactor.interfaces.LoadItemsInteractor
import com.example.cubosfilmes.model.Movie
import com.example.cubosfilmes.presenter.interfaces.MainPresenter
import com.example.cubosfilmes.view.interfaces.MainView


class MainPresenterImpl(mainView: MainView, private val findItemsInteractor: LoadItemsInteractor, page: Int) : MainPresenter, LoadItemsInteractor.OnFinishedListener {

    override fun fetchPopularMovies(page: Int) {
        findItemsInteractor.fetchPopularMovies(this, page)
    }

    private var mainView: MainView? = null
    private var page = 1

    override fun searchMovie(query: String) {
        if (mainView != null) {
            mainView!!.showProgress()
        }
        findItemsInteractor.searchMovie(this, query)
    }


    override fun onItemLongClick(position: Int) {
        if (mainView != null) {
            mainView!!.showMessage("Posição " + (position + 1) + " clicada")
        }
    }

    init {
        this.mainView = mainView
        this.page = page
    }

    override fun onResume() {
        if (mainView != null) {
            mainView!!.showProgress()
        }
        findItemsInteractor.fetchPopularMovies(this, page)
    }

    override fun onItemClicked(position: Int) {
        if (mainView != null) {
            mainView!!.showMessage("Posição " + (position + 1) + " clicada")
        }
    }

    override fun onDestroy() {
        mainView = null
    }

    override fun onFinished(items: List<Movie>?) {
        if (mainView != null && items != null) {
            mainView!!.populateRecyclerMovies(items)
            mainView!!.hideProgress()
        }

    }

    override fun onFailure(t: Throwable) {
        if (mainView != null) {
            mainView!!.hideProgress()
        }
    }
}
