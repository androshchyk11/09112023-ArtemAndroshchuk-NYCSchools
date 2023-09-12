package com.example.a20230911_artemandroschuk_nycshools.domain.result

sealed class BaseResult<out T : Any> {

    class Success<out T : Any>(val value: T) : BaseResult<T>()

    class Error(val cause: Throwable) : BaseResult<Nothing>()
}

fun <T : Any> Result<T>.foldResult(): BaseResult<T> = fold(
    onSuccess = {
        BaseResult.Success(it)
    },
    onFailure = {
        println(it)
        BaseResult.Error(it)
    }
)

