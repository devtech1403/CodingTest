package com.codingtest.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingtest.data.model.DogBreed
import com.codingtest.data.repository.MainRepository
import com.codingtest.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SelectBreedViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val dogsBreeds = MutableLiveData<Resource<List<DogBreed>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchDogsBreed()
    }

    private fun fetchDogsBreed() {
        dogsBreeds.postValue(Resource.loading(null))

        compositeDisposable.add(
            mainRepository.getDogsBreed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ breedList ->
                    dogsBreeds.postValue(Resource.success(breedList))
                }, {
                    dogsBreeds.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getDogsBreed(): LiveData<Resource<List<DogBreed>>> {
        return dogsBreeds
    }
}