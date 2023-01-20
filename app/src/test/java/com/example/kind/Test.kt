package com.example.kind.model.service.impl

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Test

internal class Test {
    val account = AccountServiceImpl()


    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun cleanUp() {
        Dispatchers.setMain(Dispatchers.Default)
    }

    @Test
    fun loginTest() {
        val answer = 4
        assertEquals(answer, 2+2)
    }

}