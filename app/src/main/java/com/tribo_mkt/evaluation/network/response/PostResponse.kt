package com.tribo_mkt.evaluation.network.response

import com.google.gson.annotations.SerializedName
import com.tribo_mkt.evaluation.domain.Post

class PostResponse(
    @SerializedName("userId") val usuarioId: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val titulo: String,
    @SerializedName("body") val conteudo: String,
    var comentarios: Int? = null
)

fun List<PostResponse>.asDomainModel(): List<Post> {
    return map {
        Post(
            userId = it.usuarioId,
            id = it.id,
            title = it.titulo,
            body = it.conteudo,
            comentarios = it.comentarios
        )
    }
}