package com.example.mymovies.ui.activity.moviesingenre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.MoviesInGenre
import com.example.mymovies.R
import com.example.mymovies.databinding.ItemListBinding
import com.example.mymovies.databinding.RecyclerItemBinding
import com.example.mymovies.helper.loadImage
import timber.log.Timber

class MoviesInGenreAdapter : RecyclerView.Adapter<MoviesInGenreAdapter.UserViewHolder>() {

    var onItemClick: ((MoviesInGenre) -> Unit)? = null

    private val mData = ArrayList<MoviesInGenre>()

    fun setData(newListData: List<MoviesInGenre>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): MoviesInGenreAdapter.UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: MoviesInGenreAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: MoviesInGenre) {
            with(binding) {

                Timber.d("check value image movies in genre adapter : ${data.title}")

//                // concat string
                ivPosterPopular.loadImage(itemView.context,"https://image.tmdb.org/t/p/original${data.posterPath}")

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}