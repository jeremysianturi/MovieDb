package com.example.mymovies.ui.activity.choosevideo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.TrailerVideo
import com.example.mymovies.R
import com.example.mymovies.databinding.LayoutItemVideoBinding

class ChooseVideoAdapter : RecyclerView.Adapter<ChooseVideoAdapter.UserViewHolder>() {

    var onItemClick: ((TrailerVideo) -> Unit)? = null

    private val mData = ArrayList<TrailerVideo>()

    fun setData(newListData: List<TrailerVideo>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ChooseVideoAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_video, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ChooseVideoAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = LayoutItemVideoBinding.bind(itemView)
        fun bind(data: TrailerVideo) {
            with(binding) {

                val key = mData[adapterPosition].key
                val link = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/$key\" frameborder=\"0\" allowfullscreen></iframe>"
                binding.webView.loadData(link, "text/html", "utf-8")

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }

            binding.webView.settings.javaScriptEnabled = true
            binding.webView.webChromeClient = object : WebChromeClient(){

            }
        }


    }

}