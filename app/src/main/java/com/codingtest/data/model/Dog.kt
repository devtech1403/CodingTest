package com.codingtest.data.model

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("width")
    val width: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("breeds")
    val breeds: List<BreedsItem>?,
    @SerializedName("height")
    val height: Int = 0
)