package com.welopes.evaluation.network.response

import com.google.gson.annotations.SerializedName
import com.welopes.evaluation.domain.Album

class AlbumResponse(
    @SerializedName("userId") val userId: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String
)

fun List<AlbumResponse>.asDomainModel(): List<Album> {
    return map {
        Album(
            userId = it.userId,
            id = it.id,
            title = it.title
        )
    }
}