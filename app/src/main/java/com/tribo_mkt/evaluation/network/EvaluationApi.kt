package com.tribo_mkt.evaluation.network

import com.tribo_mkt.evaluation.network.response.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface EvaluationApi {

    @GET("users")
    fun getAllUsers(): Deferred<Response<List<UserResponse>>>
}