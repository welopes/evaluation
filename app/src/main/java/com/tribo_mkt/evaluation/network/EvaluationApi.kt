package com.tribo_mkt.evaluation.network

import com.tribo_mkt.evaluation.network.response.AlbumResponse
import com.tribo_mkt.evaluation.network.response.PostResponse
import com.tribo_mkt.evaluation.network.response.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EvaluationApi {

    @GET("users")
    fun getAllUsers(): Deferred<Response<List<UserResponse>>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId: Int): Deferred<Response<List<PostResponse>>>

    @GET("albums")
    fun getAlbumsByUserId(@Query("userId") userId: Int): Deferred<Response<List<AlbumResponse>>>

}