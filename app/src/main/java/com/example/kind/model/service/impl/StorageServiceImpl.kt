package com.example.kind.model.service.impl

import com.example.kind.model.User
import com.example.kind.model.service.StorageService
import com.google.firebase.database.DatabaseReference
import java.util.*

class StorageServiceImpl : StorageService {

    private lateinit var database: DatabaseReference
    private database = Firebase.database.reference

    // Users
    override suspend fun addUser(email: String, password: String){

        val userId = System.currentTimeMillis().toString()
        val user = User(userId,"", email,password,0,1)

        database.child("Users").child(userId).setValue(user)
    }
    override suspend fun deleteUser(){}

    // Subscriptions
    override suspend fun addSubscription(){}
    override suspend fun deleteSubscription(){}
    override suspend fun modifySubscriptionAmount(){}
    override suspend fun modifySubscriptionPlan(){}

    // Donations
    override suspend fun addDonation(){}
    override suspend fun deleteDonation(){}

    // Charity
    override suspend fun increaseCharityDonationNumber(){}
    override suspend fun decreaseCharityDonationNumber(){}
    override suspend fun increaseCharityDonaterNumber(){}
    override suspend fun decreaseCharityDonaterNumber(){}
    override suspend fun addCharityAdministator(){}
    override suspend fun deleteCharityAdministrator(){}
    override suspend fun addCharityArticle(){}
    override suspend fun deleteArticle(){}
}