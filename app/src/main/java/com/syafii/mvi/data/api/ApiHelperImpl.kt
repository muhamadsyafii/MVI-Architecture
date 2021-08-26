package com.syafii.mvi.data.api

import com.syafii.mvi.data.model.User

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUser(): List<User> {
        return apiService.getUsers()
    }
}