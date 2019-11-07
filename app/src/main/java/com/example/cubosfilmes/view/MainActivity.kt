package com.example.cubosfilmes.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.cubosfilmes.adapter.MoviesRecyclerAdapter
import com.example.cubosfilmes.interactor.LoadItemsInteractorImpl
import com.example.cubosfilmes.model.Movie
import com.example.cubosfilmes.presenter.MainPresenterImpl
import com.example.cubosfilmes.presenter.interfaces.MainPresenter
import com.example.cubosfilmes.view.interfaces.MainView
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL
import androidx.appcompat.widget.SearchView
import com.example.cubosfilmes.R

class MainActivity : AppCompatActivity(), MainView, AdapterView.OnItemClickListener {

    private lateinit var presenter: MainPresenter
    private lateinit var movieAdapter : MoviesRecyclerAdapter

    var loading = false

    @BindView(R.id.list_movie)
    lateinit var movieRecyclerView: RecyclerView

    @BindView(R.id.progress)
    lateinit var progressBar: ProgressBar


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.search)
        if(searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.imeOptions = EditorInfo.IME_ACTION_DONE

            searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    presenter.fetchPopularMovies(1)
                    return true
                }
            })

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    searchMovie(newText)
                    return false
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        movieRecyclerView.visibility = View.INVISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
        movieRecyclerView.visibility = View.VISIBLE
    }

    override fun populateRecyclerMovies(items: List<Movie>) {
        loadRecyclerMovie(items)
    }

    private fun loadRecyclerMovie(items: List<Movie>){
        movieRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            movieAdapter = MoviesRecyclerAdapter()
            movieAdapter.submitList(items)
            adapter = movieAdapter
            movieAdapter.onItemClick = { movieRepo ->
                val intent = newIntent(this@MainActivity, movieRepo)
                startActivity(intent)
            }
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if(newState == SCROLL_STATE_TOUCH_SCROLL){
                        loading = true
                    }
                }
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = layoutManager!!.childCount
                    val totalItemCount = layoutManager!!.itemCount
                    if (!loading && visibleItemCount >= totalItemCount) {
                        loading = false
                        presenter.fetchPopularMovies(2)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
            })
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the support action bar
        val actionBar = supportActionBar

        // Set the action bar title, subtitle and elevation
        actionBar!!.title = MainApplication.applicationContext().getString(R.string.app_name)
        actionBar.subtitle = MainApplication.applicationContext().getString(R.string.title_card)
        actionBar.elevation = 4.0F

        ButterKnife.bind(this)

        presenter = MainPresenterImpl(this, LoadItemsInteractorImpl(), 1)
    }

    private fun searchMovie(query: String){
        if(query != "")
            presenter.searchMovie(query)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.onItemClicked(position)
    }

    override fun onResume() {
        presenter.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {

        private val INTENT_MOVIE = "movie_extra"

        fun newIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(INTENT_MOVIE, movie)
            return intent
        }
    }
}
