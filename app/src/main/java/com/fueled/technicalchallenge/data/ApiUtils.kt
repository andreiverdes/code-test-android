package com.fueled.technicalchallenge.data

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

/**
 * Created by bhavya@fueled.com on 18/10/2024.
 */
object ApiUtils {

    val currentTimestamp: String = System.currentTimeMillis().toString()

    val hash: String by lazy {
        val input = currentTimestamp + "36f9f6a342c3c8d1d230033cb12ea57505a53478" + "b90b08ed09de1bfffedb245e6cd7e0ea"
        md5(input)
    }

    private fun md5(input: String): String {
        val bytes = MessageDigest.getInstance("MD5").digest(input.toByteArray(StandardCharsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}