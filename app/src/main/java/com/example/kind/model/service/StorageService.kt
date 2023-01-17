package com.example.kind.model.service

import com.example.kind.model.*
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference

interface StorageService {

    // Users
    suspend fun addUser(user: User)
    suspend fun changeUser(user: User, uid: String)
    suspend fun updateCurrentUser()
    suspend fun getUIDofCurrentUser(): String
    suspend fun updateUser(email: String, password: String)
    suspend fun deleteUser(confirmEmail: String, confirmPassword: String)
    suspend fun getSubscriptions() : List<Subscription>
    suspend fun addSubscription(amount: Double, user: String, charity: String)

    // Subscriptions
    suspend fun removeFromPortfolio(charityId: String)
    suspend fun addToPortfolio(charityid: String)
    suspend fun deleteSubscription(user : String, subscription : String)
    suspend fun modifySubscriptionAmount(subscription: Subscription, amount: Double)

    // Donations
    suspend fun addDonation(amount : Double, user : String, charity : String, Desc : String = "")
    suspend fun deleteDonation(user : String, donation : String)
    suspend fun getDonations(user: String): List<Donation>

    // Charity
    suspend fun getCharity(id: String): Charity?
    suspend fun getCharities(): List<Charity>
    suspend fun getCharitiesByCategory(category: String): List<Charity>
    suspend fun increaseCharityDonationNumber(charity: String)
    suspend fun decreaseCharityDonationNumber(charity: String)
    suspend fun increaseCharityDonaterNumber(charity: String)
    suspend fun decreaseCharityDonaterNumber(charity: String)
    suspend fun addCharityAdministator()
    suspend fun deleteCharityAdministrator()
    suspend fun getArticle(id: String): Article?
    suspend fun getArticles(id: String): List<Article>
    suspend fun getHomeArticles(id: String): List<Article>
    suspend fun addCharityArticle(articleContent : String, charity : String)
    suspend fun deleteArticle(article : String, charity : String)

    // Payments
    suspend fun getPayments(user: String): List<Payment>
    suspend fun createStripePaymentIntent(amount: Double, currency: String, charityId: String): Task<DocumentReference>
    suspend fun getClientSecret(doc: DocumentReference): String
}