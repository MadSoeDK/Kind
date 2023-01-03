package com.example.kind.model.service

interface StorageService {

    // Users
    suspend fun addUser()
    suspend fun deleteUser()

    // Subscriptions
    suspend fun addSubscription()
    suspend fun deleteSubscription()
    suspend fun modifySubscriptionAmount()
    suspend fun modifySubscriptionPlan()

    // Donations
    suspend fun addDonation()
    suspend fun deleteDonation()

    // Charity
    suspend fun increaseCharityDonationNumber()
    suspend fun decreaseCharityDonationNumber()
    suspend fun increaseCharityDonaterNumber()
    suspend fun decreaseCharityDonaterNumber()
    suspend fun addCharityAdministator()
    suspend fun deleteCharityAdministrator()
    suspend fun addCharityArticle()
    suspend fun deleteArticle()
}