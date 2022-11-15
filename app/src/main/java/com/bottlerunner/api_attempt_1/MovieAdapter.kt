package com.bottlerunner.api_attempt_1

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.TextView
import com.bottlerunner.api_attempt_1.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MoviesAdapter(
    private val movies: List<Movie>,
    private val rowLayout: Int,
    private val context: Context
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    //A view holder inner class where we get reference to the views in the layout using their ID
    class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var moviesLayout: LinearLayout
        var movieTitle: TextView
        var data: TextView
        var movieDescription: TextView
        var rating: TextView
        var movieImage: ImageView

        init {
            moviesLayout = v.findViewById<View>(R.id.movies_layout) as LinearLayout
            movieImage = v.findViewById<View>(R.id.movie_image) as ImageView
            movieTitle = v.findViewById<View>(R.id.title) as TextView
            data = v.findViewById<View>(R.id.date) as TextView
            movieDescription = v.findViewById<View>(R.id.description) as TextView
            rating = v.findViewById<View>(R.id.rating) as TextView
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return MoviesAdapter.MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        val image_url: String =
            MoviesAdapter.Companion.IMAGE_URL_BASE_PATH + movies[position].posterPath
        Picasso.with(context)
            .load(image_url)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .into(holder.movieImage)
        holder.movieTitle.setText(movies[position].title)
        holder.data.setText(movies[position].releaseDate)
        holder.movieDescription.setText(movies[position].overview)
//        holder.rating.setText(movies[position].voteAverage.toInt())
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    companion object {
        const val IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w342//"
    }
}