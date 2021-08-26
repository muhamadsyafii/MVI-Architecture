package com.syafii.mvi.data.api

import com.syafii.mvi.data.model.User

interface ApiHelper {
    suspend fun getUser() : List<User>
}