package com.codingtest.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getDogsBreed() = apiService.getDogsBreed()

    fun getDogsBreedData(id: Int) = apiService.getDogsBreedData(id)
}