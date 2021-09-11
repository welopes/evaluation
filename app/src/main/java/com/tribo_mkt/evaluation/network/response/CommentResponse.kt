package com.tribo_mkt.evaluation.network.response

import com.google.gson.annotations.SerializedName
import com.tribo_mkt.evaluation.domain.Comment

class CommentResponse(
    @SerializedName("postId") val postagemId: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val nome: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val conteudo: String
)

fun List<CommentResponse>.asDomainModel(): List<Comment> {
    return map {
        Comment(
            postId = it.postagemId,
            id = it.id,
            name = it.nome,
            email = it.email,
            body = it.conteudo
        )
    }
}