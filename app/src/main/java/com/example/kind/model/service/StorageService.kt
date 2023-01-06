package com.example.kind.model.service

import com.google.firebase.firestore.CollectionReference

interface StorageService {

    // Users
    suspend fun addUser(email: String, password: String)
    suspend fun deleteUser()

    // Subscriptions
    suspend fun getSubscription(userPath : String) : CollectionReference
    suspend fun addSubscription(amount : Double, user : String, charity : String)
    suspend fun deleteSubscription(user : String, subscription : String)
    suspend fun modifySubscriptionAmount(user : String, subscription : String, amount : Double)

    // Donations
    suspend fun addDonation(amount : Double, user : String, charity : String, Desc : String = "")
    suspend fun deleteDonation(user : String, donation : String)

    // Charity
    suspend fun increaseCharityDonationNumber(charity : String)
    suspend fun decreaseCharityDonationNumber(charity : String)
    suspend fun increaseCharityDonationNumber(amount :Int, charity : String)
    suspend fun decreaseCharityDonationNumber(amount :Int, charity : String)
    suspend fun increaseCharityDonaterNumber(charity : String)
    suspend fun decreaseCharityDonaterNumber(charity : String)
    suspend fun increaseCharityDonaterNumber(amount :Int, charity : String)
    suspend fun decreaseCharityDonaterNumber(amount :Int, charity : String)
    suspend fun addCharityAdministator()
    suspend fun deleteCharityAdministrator()
    suspend fun addCharityArticle(articleContent : String, charity : String)
    suspend fun deleteArticle(article : String, charity : String)
}