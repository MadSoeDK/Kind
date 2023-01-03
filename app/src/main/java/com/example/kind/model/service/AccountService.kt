package com.example.kind.model.service

interface AccountService {
    val userid : String
    var hasUser : Boolean

    suspend fun authenticateUser(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
    suspend fun changePassword()
}