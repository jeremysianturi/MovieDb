package com.example.mymovies.ui.activity.moviesingenre.review

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Review
import com.example.mymovies.R
import com.example.mymovies.databinding.ItemListReviewBinding
import com.example.mymovies.helper.loadImage
import timber.log.Timber

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.UserViewHolder>() {

    var onItemClick: ((Review) -> Unit)? = null

    private val mData = ArrayList<Review>()

    fun setData(newListData: List<Review>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ReviewAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_review, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ReviewAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListReviewBinding.bind(itemView)
        fun bind(data: Review) {
            with(binding) {

//                // concat string
                if (data.avatarPath == ""){
                    ivProfilepicReviewer.setImageResource(R.drawable.ic_no_profile)
                } else if (data.avatarPath.contains("https")){
                    ivProfilepicReviewer.loadImage(itemView.context, data.avatarPath)
                } else {
                    ivProfilepicReviewer.loadImage(itemView.context,"https://image.tmdb.org/t/p/original${data.avatarPath}")
                }

                tvNameReviewer.text = data.name
//                tvReview.text = data.content
                tvReview.text = data.content

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}