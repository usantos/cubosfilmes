package com.example.cubosfilmes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cubosfilmes.R
import com.example.cubosfilmes.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Movie> = ArrayList()
    var onItemClick: ((Movie) -> Unit)? = null

    fun submitList(gitRepoList: List<Movie>){
        items = gitRepoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when(holder){
            is RepositoryViewHolder -> {
                holder.bind(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class RepositoryViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        private val movieName: TextView = itemView.tv_item_name
        private val popularityCounter: TextView = itemView.popularity_text_view
        private val movieImage: ImageView = itemView.movie_image

        fun bind(movie: Movie){
            movieName.text = movie.title
            popularityCounter.text = movie.popularity.toString()
            Glide.with(itemView.context).load(movie.posterUrl).into(movieImage)
            itemView.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }
}