package com.udacity.asteroidradar.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroids(
    val links: Links,
    @Json(name = "element_count") val elementCount: Int
) : Parcelable

@Parcelize
data class Links(
    val next: String,
    val prev: String,
    val self: String
) : Parcelable

//@Parcelize
//data class asteroid(
//    val id: String,
//    @Json(name = "absolute_magnitude_h") val absoluteMagnitude: Double,
//
//)

