package com.example.mymovies.ui.fragment.moviesgenre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.MoviesGenre
import com.example.mymovies.R
import com.example.mymovies.databinding.ItemMoviesGenreBinding
import timber.log.Timber

class MoviesGenreAdapter : RecyclerView.Adapter<MoviesGenreAdapter.UserViewHolder>() {

    var onItemClick: ((MoviesGenre) -> Unit)? = null

    private val mData = ArrayList<MoviesGenre>()

    fun setData(newListData: List<MoviesGenre>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): MoviesGenreAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_movies_genre, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: MoviesGenreAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMoviesGenreBinding.bind(itemView)
        fun bind(data: MoviesGenre) {
            with(binding) {

                Timber.d("check value image coming soon adapter : ${data.name}")

//                // concat string
//                ivPoster.loadImage(itemView.context,"https://image.tmdb.org/t/p/original${data.posterPath}")
                tvTitleMoviesGenre.text = data.name

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}