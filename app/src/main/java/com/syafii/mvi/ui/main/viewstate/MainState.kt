package com.syafii.mvi.ui.main.viewstate

import com.syafii.mvi.data.model.User

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Users(val users: List<User>) : MainState()
    data class Error(val error : String?) : MainState()
}