package com.example.kind.model.service.impl

import com.example.kind.model.service.AccountService

class AccountServiceImpl : AccountService {
    override val userid: String
        get() = TODO("Not yet implemented")
    override var hasUser: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}

    override suspend fun authenticateUser(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAccount() {
        TODO("Not yet implemented")
    }

    override suspend fun changePassword() {
        TODO("Not yet implemented")
    }
}