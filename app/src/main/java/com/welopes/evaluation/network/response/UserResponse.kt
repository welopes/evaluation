package com.welopes.evaluation.network.response

import com.welopes.evaluation.domain.User

data class UserResponse(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val phone: String
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