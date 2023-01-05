package com.example.kind.model.service

import com.example.kind.model.User

interface StorageService {

    // Users
    suspend fun addUser(user: User)
    suspend fun deleteUser(userId : String)

    // Subscriptions
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