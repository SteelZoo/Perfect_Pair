package com.olduo.last_dance.data.datasource.util

import retrofit2.Response

fun <T> Response<T>.toResult(): Result<T>{
    if (this.isSuccessful && this.body() != null){
        return Result.success(this.body()!!)
    } else {
        return Result.failure(Exception())
    }
}