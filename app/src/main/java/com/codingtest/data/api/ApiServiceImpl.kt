package com.codingtest.data.api

import com.codingtest.data.api.ApiEndPoint.ENDPOINT_BREED
import com.codingtest.data.api.ApiEndPoint.ENDPOINT_BREED_DATA
import com.codingtest.data.model.Dog
import com.codingtest.data.model.DogBreed
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getDogsBreed(): Single<List<DogBreed>> {
        return Rx2AndroidNetworking.get(ENDPOINT_BREED)
            .build()
            .getObjectListSingle(DogBreed::class.java)
    }

    override fun getDogsBreedData(id: Int): Single<List<Dog>> {
        return Rx2AndroidNetworking.get("$ENDPOINT_BREED_DATA?limit=100&size=full&breed_id=$id&sub_id=demo-3211cd")
            .build()
            .getObjectListSingle(Dog::class.java)
    }
}