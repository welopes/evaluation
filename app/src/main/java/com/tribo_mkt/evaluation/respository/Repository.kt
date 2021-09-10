package com.tribo_mkt.evaluation.respository

import com.tribo_mkt.evaluation.domain.User
import com.tribo_mkt.evaluation.network.EvaluationApi
import com.tribo_mkt.evaluation.network.response.UserResponse
import com.tribo_mkt.evaluation.network.response.asDomainModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class Repository(private val api: EvaluationApi) {

    suspend fun getAllUsers(): Deferred<List<User>?> {
         return withContext(Dispatchers.IO) {
            async {
                try {
                    var users = api.getAllUsers().await().body() as List<UserResponse>
                    users.sortedWith(Comparator { s1, s2 -> s1.name.compareTo(s2.name) }).asDomainModel()
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }
    }
}