package com.android.freelance.album.data.db.network.webservice

import java.lang.Exception

class Result <T> private constructor(val status: Result.Status,
                                     val data: T?,
                                     val exception: Exception?){
    enum class Status{
        SUCCESS, ERROR, LOADING
    }

    companion object{
        fun<T> success(data: T?): Result<T>{
            return Result(Status.SUCCESS, data, null)
        }

        fun<T> error(exception: Exception?): Result<T>{
            return Result(Status.ERROR, null, exception)
        }

        fun<T> loading(data: T?): Result<T>{
            return Result(Status.LOADING, data, null)
        }
    }

}