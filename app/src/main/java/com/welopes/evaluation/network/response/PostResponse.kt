package com.welopes.evaluation.network.response

import com.google.gson.annotations.SerializedName
import com.welopes.evaluation.domain.Post

class PostResponse(
    @SerializedName("userId") val userId: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    var comentarios: Int? = null
)

fun List<PostResponse>.asDomainModel(): List<Post> {
    return map {
        Post(
            userId = it.userId,
            id = it.id,
            title = it.title,
            body = it.body,
            comentarios = it.comentarios
        )
    }
}