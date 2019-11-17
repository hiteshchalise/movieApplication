package com.hites.movieapplication.presentation.ui.mainactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.hites.movieapplication.R
import com.hites.movieapplication.domain.model.Movie

class NowPlayingViewAdapter(private val ctx: Context, private val listener: (Int) -> Unit) :
    ListAdapter<Movie, NowPlayingViewAdapter.MyViewHolder>(
        DIFF_CALLBACK
    ) {

    class MyViewHolder(itemView: View, listener: (Int) -> Unit, getId: (Int) -> Int) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.item_title)
        var imageView: ImageView = itemView.findViewById(R.id.item_image)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(getId(position))
                }

            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_layout, viewGroup, false)
        return MyViewHolder(
            v,
            listener,
            {
                getItem(it).id
            }
        )
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        val tempTitle = getItem(i).title
        myViewHolder.title.text = tempTitle
        myViewHolder.imageView.load("https://image.tmdb.org/t/p/w500/" + getItem(i).poster_path) {
            crossfade(true)
            placeholder(CircularProgressDrawable(ctx))
            transformations(RoundedCornersTransformation(12.0f))
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.poster_path == newItem.poster_path &&
                        oldItem.adult == newItem.adult &&
                        oldItem.vote_average == newItem.vote_average
            }
        }
    }

}
