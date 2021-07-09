package com.codingtest.data.repository

import com.codingtest.data.api.ApiHelper
import com.codingtest.data.model.Dog
import com.codingtest.data.model.DogBreed
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getDogsBreed(): Single<List<DogBreed>> {
        return apiHelper.getDogsBreed()
    }

    fun getDogsBreedData(id: Int): Single<List<Dog>> {
        return apiHelper.getDogsBreedData(id)
    }
}