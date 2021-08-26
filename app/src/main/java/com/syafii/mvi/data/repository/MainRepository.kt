package com.syafii.mvi.data.repository

import com.syafii.mvi.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUser()
}