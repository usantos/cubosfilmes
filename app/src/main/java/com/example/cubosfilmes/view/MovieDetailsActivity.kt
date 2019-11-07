package com.example.cubosfilmes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.cubosfilmes.R
import com.example.cubosfilmes.model.Movie

class MovieDetailsActivity : AppCompatActivity() {

    @BindView(R.id.movie_title)
    lateinit var movieTitle: TextView

    @BindView(R.id.movie_image)
    lateinit var movieImage: ImageView

    @BindView(R.id.movie_overview)
    lateinit var movieOverview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        ButterKnife.bind(this)

        var movieExtra = intent.getSerializableExtra("movie_extra") as Movie

        movieTitle.text = movieExtra.title
        movieOverview.text = movieExtra.overview
        Glide.with(this).load(movieExtra.backdropUrl).into(movieImage)
    }
}
