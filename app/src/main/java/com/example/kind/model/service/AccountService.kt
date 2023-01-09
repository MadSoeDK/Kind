package com.example.kind.model.service

interface AccountService {
    val userid : String
    val hasUser : Boolean

    suspend fun authenticateUser(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
    suspend fun changePassword(email: String, password: String)
    suspend fun signInWithEmailAndPassword(email: String, password : String)
}