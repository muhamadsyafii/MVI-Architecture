package com.syafii.mvi.util


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syafii.mvi.data.api.ApiHelper
import com.syafii.mvi.data.repository.MainRepository
import com.syafii.mvi.ui.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}