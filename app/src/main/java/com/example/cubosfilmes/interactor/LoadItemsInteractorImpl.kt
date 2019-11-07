package com.example.cubosfilmes.interactor

import android.util.Log
import com.example.cubosfilmes.R
import com.example.cubosfilmes.interactor.interfaces.LoadItemsInteractor
import com.example.cubosfilmes.network.ApiClient
import com.example.cubosfilmes.responses.MovieListResponse
import com.example.cubosfilmes.view.MainApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadItemsInteractorImpl : LoadItemsInteractor {

    private val TAG = "LoadItemsInteractorImpl"

    override fun searchMovie(onFinishedListener: LoadItemsInteractor.OnFinishedListener?,query: String) {
        val call = ApiClient().apiService().searchMovie(
            MainApplication.applicationContext().getString(R.string.api_key), query)
        call.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(call: Call<MovieListResponse>,response: Response<MovieListResponse>) {
                val movies = if (response.body() != null) response.body()!!.movies else null
                onFinishedListener!!.onFinished(movies)
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
                onFinishedListener!!.onFailure(t)
            }
        })
    }


    override fun fetchPopularMovies(onFinishedListener: LoadItemsInteractor.OnFinishedListener, page: Int) {

        val call = ApiClient().apiService().fetchPopularMovies(
            MainApplication.applicationContext().getString(R.string.api_key), "pt-BR", page)
        call.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                val movies = if (response.body() != null) response.body()!!.movies else null
                Log.d(TAG, "Count Movies " + movies!!.size)
                onFinishedListener.onFinished(movies)
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
                onFinishedListener.onFailure(t)
            }
        })
    }
}
