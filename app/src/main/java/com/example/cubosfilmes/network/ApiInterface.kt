package com.example.cubosfilmes.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.cubosfilmes.responses.MovieResponse
import com.example.cubosfilmes.responses.MovieListResponse
import retrofit2.http.Path


interface ApiInterface {

    @GET("search/movie")
    fun searchMovie(@Query("api_key") apiKey: String, @Query("query") query: String): Call<MovieListResponse>

    @GET("discover/movie")
    fun discoverMovies(@Query("with_genres") withGenres: String, @Query("api_key") apiKey: String, @Query("language") language: String): Call<MovieListResponse>

    @GET("movie/popular")
    fun fetchPopularMovies(@Query("api_key") apiKey: String, @Query("language") language: String): Call<MovieListResponse>

    @GET("movie/popular")
    fun fetchPopularMovies(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int): Call<MovieListResponse>

    @GET("movie/top_rated")
    fun fetchHighestRatedMovies(@Query("api_key") apiKey: String, @Query("language") language: String): Call<MovieListResponse>

    @GET("movie/top_rated")
    fun fetchHighestRatedMovies(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int): Call<MovieListResponse>

    @GET("movie/{id}")
    fun fetchMovie(@Path("id") movieId: Int, @Query("api_key") apiKey: String): Call<MovieResponse>
}
