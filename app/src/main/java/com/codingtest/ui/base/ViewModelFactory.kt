package com.codingtest.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codingtest.data.api.ApiHelper
import com.codingtest.data.repository.MainRepository
import com.codingtest.ui.main.viewmodel.MainViewModel
import com.codingtest.ui.main.viewmodel.SelectBreedViewModel
import com.codingtest.utils.Constant.UNKNOWN_CLASS_NAME

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        } else if (modelClass.isAssignableFrom(SelectBreedViewModel::class.java)) {
            return SelectBreedViewModel(MainRepository(apiHelper)) as T
        }

        throw IllegalArgumentException(UNKNOWN_CLASS_NAME)
    }
}