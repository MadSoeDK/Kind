package com.example.kind.model.service.impl

import com.example.kind.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

/**
 * This class were meant for unit test our methods, but we couldn't get past a error: 'ExceptionInInitializerError'
 */

class AccountServiceImplTest {
    val account = AccountServiceImpl()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @org.junit.Test
    fun loginTest() = runBlocking {
        account.authenticateUser("a@a.dk", "abcdef")

        val email = Firebase.auth.currentUser?.email.toString()

        assertEquals("a@a.dk", email)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanUp() {
        Dispatchers.setMain(Dispatchers.Default)
    }
}