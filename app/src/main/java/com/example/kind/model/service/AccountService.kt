package com.example.kind.model.service

interface AccountService {
    suspend fun authenticateUser(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
    suspend fun changePassword(email: String, password: String)
    suspend fun createUserWithEmailAndPassword(email: String, password : String)
    suspend fun reAuthentication(email: String, password: String)
    suspend fun updateUser(email: String, password: String)
}