package com.codingtest.data.api

import com.codingtest.BuildConfig

object ApiEndPoint {
    const val ENDPOINT_BREED: String = BuildConfig.BASE_URL + "/breeds"
    const val ENDPOINT_BREED_DATA: String = BuildConfig.BASE_URL + "/images/search"
}