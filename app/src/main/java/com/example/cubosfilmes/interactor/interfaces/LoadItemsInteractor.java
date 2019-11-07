package com.example.cubosfilmes.interactor.interfaces;

import com.example.cubosfilmes.model.Movie;

import java.util.List;

public interface LoadItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<Movie> moviesArrayList);
        void onFailure(Throwable t);
    }

    void fetchPopularMovies(OnFinishedListener onFinishedListener, int page);

    void searchMovie(OnFinishedListener onFinishedListener, String query);
}
