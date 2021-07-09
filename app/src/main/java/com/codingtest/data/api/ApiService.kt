package com.codingtest.data.api

import com.codingtest.data.model.Dog
import com.codingtest.data.model.DogBreed
import io.reactivex.Single

interface ApiService {

    fun getDogsBreed(): Single<List<DogBreed>>

    fun getDogsBreedData(id: Int): Single<List<Dog>>
}