package com.codingtest.data.model

import com.google.gson.annotations.SerializedName

data class Weight(
    @SerializedName("metric")
    val metric: String = "",
    @SerializedName("imperial")
    val imperial: String = ""
)