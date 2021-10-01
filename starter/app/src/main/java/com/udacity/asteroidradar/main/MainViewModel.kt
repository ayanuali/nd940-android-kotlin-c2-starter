package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.network.AsteroidApi
import com.udacity.asteroidradar.network.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    init {
        Log.v("com.ayanuali.test", "testing")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val data =
                    AsteroidApi.retrofitService.getAsteroids(
                        "2015-09-07",
                        "2015-09-08",
                        Constants.API_KEY
                    ).await()
                Log.v("com.ayanuali.test", "another test " + data)
            }
        }
    }
}