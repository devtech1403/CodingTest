package com.codingtest.data.model

import com.google.gson.annotations.SerializedName

data class DogBreed(
    @SerializedName("image")
    val image: Image,
    @SerializedName("life_span")
    val lifeSpan: String = "",
    @SerializedName("breed_group")
    val breedGroup: String = "",
    @SerializedName("temperament")
    val temperament: String = "",
    @SerializedName("origin")
    val origin: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("weight")
    val weight: Weight,
    @SerializedName("bred_for")
    val bredFor: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("reference_image_id")
    val referenceImageId: String = "",
    @SerializedName("height")
    val height: Height
)