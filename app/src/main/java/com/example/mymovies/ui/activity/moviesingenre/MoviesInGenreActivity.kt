package com.example.mymovies.ui.activity.moviesingenre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.domain.model.DetailMovie
import com.example.mymovies.R
import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.databinding.ActivityMoviesInGenreBinding
import com.example.mymovies.ui.activity.moviesingenre.review.ReviewAdapter
import com.example.mymovies.ui.activity.moviesingenre.review.ReviewViewModel
import com.example.mymovies.ui.fragment.moviedetail.DetailMovieActivity
import com.example.mymovies.ui.fragment.moviedetail.DetailMovieViewModel
import com.example.mymovies.ui.fragment.popularmovies.PopularMoviesAdapterGrid
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MoviesInGenreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesInGenreBinding
    private lateinit var adapterMoviesInGenre : MoviesInGenreAdapter
    private val moviesInGenreViewModel : MoviesInGenreViewModel by viewModels()


    private var apiKey = "4e017aafa0c4da4d663bc40fa6d6afe0"
    private var language = "en-US"
    private var sortBy = "popularity.desc"
    private var includeAdult = false
    private var includeVideo = false
    private var page = "1"
    private var withWatchMonetizationTypes = "flatrate"
    private lateinit var idChoosen: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesInGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idChoosen = intent.getIntExtra("id_choosen",520763).toString()
        setupObserverMoviesInGenre(apiKey, language, sortBy, includeAdult, includeVideo, page, withWatchMonetizationTypes, idChoosen)
        buildListMoviesInGenre()

        // search
        binding.layoutToolbar.edtSearch.doOnTextChanged { text, start, before, count ->
            moviesInGenreViewModel.searchQuery.value = text.toString()
        }
    }

    private fun setupObserverMoviesInGenre(apiKey: String, language: String, sortBy: String, includeAdult: Boolean, includeVideo: Boolean, page: String, withWatchMonetizationTypes: String, withGenres: String) {

        moviesInGenreViewModel.getMoviesInGenre(apiKey, language, sortBy, includeAdult, includeVideo, page, withWatchMonetizationTypes, withGenres).observe(this) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarMoviesInGenre.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarMoviesInGenre.visibility = View.GONE
                        adapterMoviesInGenre.setData(data.data)
                        Timber.d("observer_moviesingenre_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarMoviesInGenre.visibility = View.GONE
                        Timber.d("error_message moviesingenre : ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(parentFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }

        moviesInGenreViewModel.search.observe(this) { data ->
            adapterMoviesInGenre.setData(data)

        }
    }

    private fun buildListMoviesInGenre() {

        adapterMoviesInGenre = MoviesInGenreAdapter()
        binding.rvGridviewMoviesInGenre.setHasFixedSize(true)
        binding.rvGridviewMoviesInGenre.layoutManager = GridLayoutManager(this,2)
//            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvGridviewMoviesInGenre.adapter = adapterMoviesInGenre

        binding.rvGridviewMoviesInGenre.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )

        adapterMoviesInGenre.onItemClick = { selectData ->
            val mIntent = Intent(this, DetailMovieActivity::class.java)
            mIntent.putExtra("id_choosen", selectData.id)
            mIntent.putExtra("overview", selectData.overview)
            mIntent.putExtra("posterPath", selectData.posterPath)
            mIntent.putExtra("title", selectData.title)
            mIntent.putExtra("release_date", selectData.releaseDate)
            startActivity(mIntent)
        }
    }
}