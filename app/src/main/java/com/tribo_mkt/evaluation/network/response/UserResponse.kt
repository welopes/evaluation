package com.tribo_mkt.evaluation.network.response

import com.google.gson.annotations.SerializedName
import com.tribo_mkt.evaluation.domain.User

data class UserResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String
)

fun List<UserResponse>.asDomainModel(): List<User> {
    return map {
        User(
            id = it.id,
            name = it.name,
            userName = it.username,
            email = it.email,
            phone = it.phone
        )
    }
}