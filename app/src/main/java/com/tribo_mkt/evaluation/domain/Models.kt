package com.tribo_mkt.evaluation.domain

data class User(
    val id: String,
    val name: String,
    val userName: String,
    val email: String,
    val phone: String
)

data class Post(
    val userId: String,
    val id: String,
    val title: String,
    val body: String,
    var comentarios: Int? = null
)

data class Photo(
    val albumId: String,
    val id: String,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

data class Comment(
    val postId: String,
    val id: String,
    val name: String,
    val email: String,
    val body: String
)

data class Album(
    val userId: String,
    val id: String,
    val title: String
)
