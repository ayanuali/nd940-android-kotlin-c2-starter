package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NetworkUtils.addToTodaysDate
import com.udacity.asteroidradar.api.NetworkUtils.getTodaysDate
import com.udacity.asteroidradar.api.NetworkUtils.parseAsteroidsJsonResult
import com.udacity.asteroidradar.network.AsteroidApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                AsteroidApi.retrofitService.getAsteroids(
                    getTodaysDate(),
                    addToTodaysDate(1),
                    Constants.API_KEY
                ).enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<String>,
                        response: Response<String>
                    ) {
                        val data = response.body()?.let {
                            parseAsteroidsJsonResult(JSONObject(it))
                        }

                        val test = "string"

                        Log.v("com.ayanuali.test", "another test " + data)
                    }
                })
            }
        }
    }
}