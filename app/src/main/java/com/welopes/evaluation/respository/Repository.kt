package com.welopes.evaluation.respository

import com.welopes.evaluation.domain.Album
import com.welopes.evaluation.domain.Comment
import com.welopes.evaluation.domain.Post
import com.welopes.evaluation.domain.User
import com.welopes.evaluation.network.EvaluationApi
import com.welopes.evaluation.network.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class Repository(private val api: EvaluationApi) {

    suspend fun getAllUsers(): List<User>? {
        return withContext(Dispatchers.IO) {
            async {
                var users = api.getAllUsers().await().body() as List<UserResponse>
                users.sortedWith(compareBy { it.name }).asDomainModel()
            }
        }.await()
    }

    suspend fun getPostsByUserId(userId: Int): List<Post>? {
        return withContext(Dispatchers.IO) {
            async {
                var posts = api.getPostsByUserId(userId).await().body() as List<PostResponse>
                posts.asDomainModel()
            }
        }.await()
    }

    suspend fun getAlbumsByUserId(userId: Int): List<Album>? {
        return withContext(Dispatchers.IO) {
            async {
                var albums = api.getAlbumsByUserId(userId).await().body() as List<AlbumResponse>
                albums.asDomainModel()
            }
        }.await()
    }

    suspend fun getCommentsByUserId(userId: Int): List<Comment>? {
        return withContext(Dispatchers.IO) {
            async {
                var comments = api.getCommentsByUserId(userId).await().body() as List<CommentResponse>
                comments.asDomainModel()
            }
        }.await()
    }

    suspend fun getCommentsByPostId(postId: Int): List<Comment>? {
        return withContext(Dispatchers.IO) {
            async {
                var comments = api.getCommentsByPostId(postId).await().body() as List<CommentResponse>
                comments.asDomainModel()
            }
        }.await()
    }
}