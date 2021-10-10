package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NetworkUtils.addToTodaysDate
import com.udacity.asteroidradar.api.NetworkUtils.getTodaysDate
import com.udacity.asteroidradar.api.NetworkUtils.moshiConverter
import com.udacity.asteroidradar.api.NetworkUtils.parseAsteroidsJsonResult
import com.udacity.asteroidradar.network.AsteroidApi
import com.udacity.asteroidradar.network.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _pictureOfTheDay = MutableLiveData<PictureOfDay>()
    val pictureOfTheDay: LiveData<PictureOfDay>
        get() = _pictureOfTheDay

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.v("com.ayanuali.test", "test1111")
                AsteroidApi.retrofitService.getAsteroids(
                    getTodaysDate(),
                    addToTodaysDate(1),
                    Constants.API_KEY
                ).enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.v("com.ayanuali.test", "test1111222eee")
                    }

                    override fun onResponse(
                        call: Call<String>,
                        response: Response<String>
                    ) {
                        Log.v("com.ayanuali.test", "test1111122222")
                        val data = response.body()?.let {
                            parseAsteroidsJsonResult(JSONObject(it))
                        }

                        val test = "string"

                        Log.v("com.ayanuali.test", "another test " + data)
                    }
                })


                AsteroidApi.retrofitService.getPictureOfDay(
                    Constants.API_KEY
                ).enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.v("com.ayanuali.test", "test1111333 eee")
                    }

                    override fun onResponse(
                        call: Call<String>,
                        response: Response<String>
                    ) {
                        Log.v("com.ayanuali.test", "test111133333   ")
                        _pictureOfTheDay.value = response.body()?.let {
                            moshiConverter(it)
                        }

                        val test = "string"

                        Log.v("com.ayanuali.test", "another test ")
                    }
                })
            }
        }
    }
}