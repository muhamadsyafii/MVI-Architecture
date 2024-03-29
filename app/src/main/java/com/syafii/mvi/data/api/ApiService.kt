package com.syafii.mvi.data.api

import com.syafii.mvi.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

}