package com.codingtest.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingtest.data.model.Dog
import com.codingtest.data.repository.MainRepository
import com.codingtest.utils.Constant.SOMETHING_WENT_WRONG
import com.codingtest.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val dogs = MutableLiveData<Resource<List<Dog>>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchDogsBreedData(id: Int) {
        dogs.postValue(Resource.loading(null))

        compositeDisposable.add(
            mainRepository.getDogsBreedData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dogList ->
                    dogs.postValue(Resource.success(dogList))
                }, {
                    dogs.postValue(Resource.error(SOMETHING_WENT_WRONG, null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getDogsBreedData(): LiveData<Resource<List<Dog>>> {
        return dogs
    }
}