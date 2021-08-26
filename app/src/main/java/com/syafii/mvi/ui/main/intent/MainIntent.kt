package com.syafii.mvi.ui.main.intent

sealed class MainIntent {
    object FetchUser : MainIntent()
}