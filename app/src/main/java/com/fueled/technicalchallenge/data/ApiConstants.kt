package com.fueled.technicalchallenge.data

import com.fueled.technicalchallenge.BuildConfig

/**
 * Created by bhavya@fueled.com on 17/10/2024.
 */
object ApiConstants {

    const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    const val PRIVATE_KEY = BuildConfig.MARVEL_PRIVATE_API_KEY
    const val PUBLIC_KEY = BuildConfig.MARVEL_PUBLIC_API_KEY
}