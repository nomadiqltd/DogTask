package com.nomadiq.chipdogstask.presentation.viewmodel

sealed class ResultDataUIState<T> {
    class Loading<T> : ResultDataUIState<T>()
    data class Success<T>(val data: T) : ResultDataUIState<T>()
    data class Error<T>(val error: String) : ResultDataUIState<T>()
    class NetworkError<T> : ResultDataUIState<T>()
}
