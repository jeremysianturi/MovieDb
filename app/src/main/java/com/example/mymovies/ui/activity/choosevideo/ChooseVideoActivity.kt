package com.example.mymovies.ui.activity.choosevideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.mymovies.databinding.ActivityChooseVideoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ChooseVideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseVideoBinding
    private lateinit var adapterChooseVideo : ChooseVideoAdapter
    private val chooseVideoViewModel : ChooseVideoViewModel by viewModels()

    private lateinit var idChoosen : String
    private var apiKey = "4e017aafa0c4da4d663bc40fa6d6afe0"
    private var language = "en-US"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup Actionbar and navigasi up
        val actionbar = supportActionBar
//        actionbar?.title = "Detail Movie"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        idChoosen = intent.getStringExtra("idChosen").toString()

        setupObserverTrailerVideo(idChoosen, apiKey,language)
        buildListTrailerVideo()

        // onclick
        onclick()

    }

    private fun setupObserverTrailerVideo(movieId: String, apiKey: String, language: String) {

        chooseVideoViewModel.getTrailerVideo(movieId, apiKey, language).observe(this) { data ->

            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarChooseVideo.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarChooseVideo.visibility = View.GONE
                        adapterChooseVideo.setData(data.data)
                        Timber.d("observer_choose_video_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarChooseVideo.visibility = View.GONE
                        Timber.d("error_message choose video : ${data.message}")
//                        ErrorBottomSheet.instance(data.message.toString(), data.message.toString())
//                            .show(parentFragmentManager, ErrorBottomSheet.TAG)
                    }
                }

            }
        }

    }

    private fun buildListTrailerVideo() {

        adapterChooseVideo = ChooseVideoAdapter()
        binding.rvVideo.setHasFixedSize(true)
        binding.rvVideo.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvVideo.adapter = adapterChooseVideo

        binding.rvVideo.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

    }

    private fun onclick() {
        binding.apply {

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}