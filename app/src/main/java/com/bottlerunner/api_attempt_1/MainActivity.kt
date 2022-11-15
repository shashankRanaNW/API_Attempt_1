package com.bottlerunner.api_attempt_1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        connectAndGetApiData()
    }

    // This method create an instance of Retrofit
    // set the base url
    fun connectAndGetApiData() {
        if (MainActivity.Companion.retrofit == null) {
            MainActivity.Companion.retrofit = Retrofit.Builder()
                .baseUrl(MainActivity.Companion.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val movieApiService: MovieApiService? =
            MainActivity.Companion.retrofit?.create<MovieApiService>(
                MovieApiService::class.java
            )
        val call: Call<MovieResponse>? =
            movieApiService?.getTopRatedMovies(MainActivity.Companion.API_KEY)
        call?.enqueue(object : Callback<MovieResponse?> {
            override fun onResponse(call: Call<MovieResponse?>?, response: Response<MovieResponse?>) {
                val movies: List<Movie>? = response.body()?.results
                recyclerView!!.adapter = movies?.let {
                    MoviesAdapter(
                        it, R.layout.list_item_movies,
                        applicationContext
                    )
                }
//                recyclerView!!.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d(MainActivity.Companion.TAG, "Number of movies received: " + movies?.size)
            }

            override fun onFailure(call: Call<MovieResponse?>?, throwable: Throwable) {
                Log.e(MainActivity.Companion.TAG, throwable.toString())
            }
        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        const val BASE_URL = "http://api.themoviedb.org/3/"
        private var retrofit: Retrofit? = null

        // insert your themoviedb.org API KEY here
        private const val API_KEY = "05be7414cbc6be1252a4cf602ff486bb"
    }
}