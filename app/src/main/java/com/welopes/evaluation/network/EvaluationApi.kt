package com.welopes.evaluation.network

import com.welopes.evaluation.network.response.AlbumResponse
import com.welopes.evaluation.network.response.CommentResponse
import com.welopes.evaluation.network.response.PostResponse
import com.welopes.evaluation.network.response.UserResponse
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

    @GET("comments")
    fun getCommentsByPostId(@Query("postId") postId: Int): Deferred<Response<List<CommentResponse>>>

    @GET("comments")
    fun getCommentsByUserId(@Query("userId") userId: Int): Deferred<Response<List<CommentResponse>>>

}