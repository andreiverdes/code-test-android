package com.fueled.technicalchallenge.data

/**
 * Created by bhavya@fueled.com on 17/10/2024.
 */
object ApiConstants {

    const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    object Headers {
        const val ts = "ts"
        const val hash = "hash"
        const val apiKey = "apikey"
        const val query = "nameStartsWith"
    }
}