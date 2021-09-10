package com.tribo_mkt.evaluation.network.response

import com.google.gson.annotations.SerializedName

class AlbumResponse(
    @SerializedName("userId") val usuarioId: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val titulo: String
)