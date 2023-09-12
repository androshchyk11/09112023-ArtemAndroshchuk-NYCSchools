package com.example.a20230911_artemandroschuk_nycshools.presentation.viewState

sealed class ViewState<out T : Any?> {
    object Loading : ViewState<Nothing>()
    class Success<out T : Any>(val result: T) : ViewState<T>()
    class Error(val errorInfo: Throwable) : ViewState<Nothing>()
}