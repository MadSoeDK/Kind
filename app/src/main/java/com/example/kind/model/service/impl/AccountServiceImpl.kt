package com.example.kind.model.service.impl

import com.example.kind.model.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth : FirebaseAuth = FirebaseAuth.getInstance()): AccountService {
    override val userid: String
        get() = auth.currentUser?.uid.orEmpty()
    override val hasUser: Boolean
        get() = auth.currentUser != null


    override suspend fun authenticateUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun changePassword(email: String, password : String) {
        auth.sendPasswordResetEmail(email)
        auth.currentUser!!.updatePassword(password).await()
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }
}