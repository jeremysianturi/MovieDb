package com.example.mymovies.ui.fragment.moviesgenre

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.domain.model.MoviesGenre
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentAwardBinding
import com.example.mymovies.databinding.FragmentHomeBinding
import com.example.mymovies.databinding.FragmentMoviesGenreBinding
import com.example.mymovies.ui.activity.moviesingenre.MoviesInGenreActivity
import com.example.mymovies.ui.fragment.home.HomeFragmentViewModel
import com.example.mymovies.ui.fragment.home.banner.SliderAdapter
import com.example.mymovies.ui.fragment.home.comingsoon.ComingSoonAdapter
import com.example.mymovies.ui.fragment.home.popularmovies.PopularMoviesAdapter
import com.example.mymovies.ui.fragment.moviedetail.DetailMovieActivity
import com.example.mymovies.ui.fragment.popularmovies.PopularMoviesAdapterGrid
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.util.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MoviesGenreFragment : Fragment() {

    private lateinit var binding: FragmentMoviesGenreBinding
    private lateinit var adapterMoviesGenre : MoviesGenreAdapter
    private val moviesGenreViewModel : MoviesGenreViewModel by viewModels()

    private var apiKey = "4e017aafa0c4da4d663bc40fa6d6afe0"
    private var language = "en-US"
    private var sortBy = "popularity.desc"
    private var includeAdult = false
    private var includeVideo = false
    private var page = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMoviesGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserverMoviesGenre(apiKey, language)
        buildListMoviesGenre()

    }

    private fun setupObserverMoviesGenre(apiKey: String, language: String) {

        moviesGenreViewModel.getMoviesGenre(apiKey, language).observe(viewLifecycleOwner) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarMoviesgenre.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarMoviesgenre.visibility = View.GONE
                        adapterMoviesGenre.setData(data.data)
                        Timber.tag(tag).d("data movies genre : ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarMoviesgenre.visibility = View.GONE
                        Timber.tag(tag).d("error_message movies genre : ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(parentFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }
    }


    private fun buildListMoviesGenre() {
        Log.d("check","test")
        adapterMoviesGenre = MoviesGenreAdapter()
        binding.rvMoviesGenre.setHasFixedSize(true)
        binding.rvMoviesGenre.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvMoviesGenre.adapter = adapterMoviesGenre

        binding.rvMoviesGenre.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )


        adapterMoviesGenre.onItemClick = { selectData ->
            val mIntent = Intent(activity, MoviesInGenreActivity::class.java)
            mIntent.putExtra("id_choosen", selectData.id)
            Log.d("movies genre","check value id chosen movies genre : ${selectData.id}")
            startActivity(mIntent)
        }
    }
}