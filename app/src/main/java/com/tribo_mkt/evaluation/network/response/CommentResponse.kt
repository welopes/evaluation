package com.tribo_mkt.evaluation.network.response

import com.google.gson.annotations.SerializedName

class CommentResponse(
    @SerializedName("postId") val postagemId: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val nome: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val conteudo: String
)