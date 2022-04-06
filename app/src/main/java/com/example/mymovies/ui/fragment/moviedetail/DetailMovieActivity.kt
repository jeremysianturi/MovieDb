package com.example.mymovies.ui.fragment.moviedetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.core.data.Resource
import com.example.core.data.source.local.room.MovieDatabase
import com.example.core.domain.model.DetailMovie
import com.example.mymovies.databinding.ActivityDetailMovieBinding
import com.example.mymovies.helper.loadImage
import com.example.mymovies.ui.activity.choosevideo.ChooseVideoActivity
import com.example.mymovies.ui.activity.moviesingenre.review.ReviewAdapter
import com.example.mymovies.ui.activity.moviesingenre.review.ReviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var adapterReview : ReviewAdapter
    private val detailMovieViewModel : DetailMovieViewModel by viewModels()
    private val reviewViewModel : ReviewViewModel by viewModels()

    private var dataDetailMovie : List<DetailMovie>? = null
    private var apiKey = "4e017aafa0c4da4d663bc40fa6d6afe0"
    private var language = "en-US"
    private var page = "1"
    private lateinit var idChoosen: String
    private lateinit var overview: String
    private lateinit var posterPath: String
    private lateinit var title: String
    private lateinit var releaseDate1: String

    // loved
    lateinit var db: MovieDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup Actionbar and navigasi up
        val actionbar = supportActionBar
//        actionbar?.title = "Detail Movie"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        db = Room.databaseBuilder(applicationContext, MovieDatabase::class.java, "loved-db").build()

        idChoosen = intent.getIntExtra("id_choosen",520763).toString()
        overview = intent.getStringExtra("overview").toString()
        posterPath = intent.getStringExtra("posterPath").toString()
        title = intent.getStringExtra("title").toString()
        releaseDate1 = intent.getStringExtra("release_date").toString()

        Timber.d("check value extra data : \n idChoosen: $idChoosen \n overview: $overview \n posterPath: $posterPath \n title: $title \n releaseDate: $releaseDate1")

        setupObserver(idChoosen, apiKey, language)
        setView()

        setupObserverReview(idChoosen, apiKey,language, page)
        buildListReview()

        // onclick
        onclick()

    }


    private fun setupObserver(movieId: String, apiKey: String, language: String) {

        detailMovieViewModel.getDetailMovie(movieId, apiKey, language).observe(this) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarDetailMovie.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarDetailMovie.visibility = View.GONE
//                        adapter.setData(data.data)
                        dataDetailMovie = data.data
//                        setView()
                        Timber.d("observer_detail_movie_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarDetailMovie.visibility = View.GONE
                        Timber.d("error_message ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(supportFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }

    }

    private fun setupObserverReview(movieId: String, apiKey: String, language: String, page: String) {

        reviewViewModel.getReview(movieId, apiKey, language, page).observe(this) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarDetailMovie.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarDetailMovie.visibility = View.GONE
                        adapterReview.setData(data.data)
                        Timber.d("observer_review_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarDetailMovie.visibility = View.GONE
                        Timber.d("error_message review : ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(parentFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }

    }

    private fun buildListReview() {

        adapterReview = ReviewAdapter()
        binding.rvMovieReview.setHasFixedSize(true)
        binding.rvMovieReview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMovieReview.adapter = adapterReview

        binding.rvMovieReview.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

    }

    private fun setView(){
        binding.apply {
//            ivPosterDetail.loadImage(this@DetailMovieActivity,"https://image.tmdb.org/t/p/original${dataDetailMovie?.get(0)?.posterPath}")
//           tvDescription.text = dataDetailMovie?.get(0)?.overview
            ivPosterDetail.loadImage(this@DetailMovieActivity,"https://image.tmdb.org/t/p/original$posterPath")
            tvDescription.text = overview
        }
    }

    private fun onclick() {
        binding.apply {
            btnWatchTrailer.setOnClickListener {
                val mIntent = Intent(this@DetailMovieActivity, ChooseVideoActivity::class.java)
                mIntent.putExtra("idChosen", idChoosen)
                startActivity(mIntent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}