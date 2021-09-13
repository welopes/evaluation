package com.welopes.evaluation.network.response

import com.google.gson.annotations.SerializedName
import com.welopes.evaluation.domain.Comment

class CommentResponse(
    @SerializedName("postId") val postId: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String
)

fun List<CommentResponse>.asDomainModel(): List<Comment> {
    return map {
        Comment(
            postId = it.postId,
            id = it.id,
            name = it.name,
            email = it.email,
            body = it.body
        )
    }
}