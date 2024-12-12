package com.fueled.technicalchallenge.domain.model


data class Page<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>,
)