package com.example.kind.model.service.impl

import com.example.kind.model.service.AccountService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountServiceImpl @Inject constructor(): AccountService {
    override suspend fun authenticateUser(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }

    override suspend fun deleteAccount() {
        Firebase.auth.currentUser!!.delete().await()
    }

    override suspend fun changePassword(email: String, password : String) {
        Firebase.auth.sendPasswordResetEmail(email)
        Firebase.auth.currentUser!!.updatePassword(password).await()
    }

    override suspend fun createUserWithEmailAndPassword(email: String, password: String) {
        val uid = Firebase.auth.createUserWithEmailAndPassword(email, password).await().user?.uid
        if (uid != null) {
            //TODO add some user error
        }
    }
}