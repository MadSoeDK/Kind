package com.example.kind.model.service.impl

import android.content.ContentValues.TAG
import android.util.Log
import com.example.kind.model.User
import com.example.kind.model.service.StorageService
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class StorageServiceImpl : StorageService {
    private val database = Firebase.firestore

    // Users
    override suspend fun addUser(user : User) {
        database.collection("Users").add(user)
    }


    override suspend fun deleteUser(userId: String) {
        database.collection("Users").document(userId).delete()
    }

    // Subscriptions
    override suspend fun addSubscription() {}
    override suspend fun deleteSubscription() {}
    override suspend fun modifySubscriptionAmount() {}
    override suspend fun modifySubscriptionPlan() {}

    // Donations
    override suspend fun addDonation() {}
    override suspend fun deleteDonation() {}

    // Charity
    override suspend fun increaseCharityDonationNumber() {}
    override suspend fun decreaseCharityDonationNumber() {}
    override suspend fun increaseCharityDonaterNumber() {}
    override suspend fun decreaseCharityDonaterNumber() {}
    override suspend fun addCharityAdministator() {}
    override suspend fun deleteCharityAdministrator() {}
    override suspend fun addCharityArticle() {}
    override suspend fun deleteArticle() {}
}