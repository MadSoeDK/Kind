package com.example.kind.model.service.impl

import com.example.kind.model.User
import com.example.kind.model.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountServiceImpl @Inject constructor(
    private val auth : FirebaseAuth = FirebaseAuth.getInstance(),
    private val storage : FirebaseFirestore = FirebaseFirestore.getInstance()
): AccountService {
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

    override suspend fun createUserWithEmailAndPassword(email: String, password: String) {
        val uid = auth.createUserWithEmailAndPassword(email, password).await().user?.uid
        if (uid != null) {
            //TODO add some user error
        }
    }
}