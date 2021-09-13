package com.welopes.evaluation.network.response

import com.google.gson.annotations.SerializedName
import com.welopes.evaluation.domain.Photo

class PhotoResponse(
    @SerializedName("albumId") val albumId: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
)

fun List<PhotoResponse>.asDomainModel(): List<Photo> {
    return map {
        Photo(
            albumId = it.albumId,
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl
        )
    }
}