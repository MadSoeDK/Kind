package com.example.kind.model.service.impl

import com.example.kind.Global
import com.example.kind.model.*
import com.example.kind.model.service.StorageService
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.collections.ArrayList

class StorageServiceImpl : StorageService {
    private val database = Firebase.firestore

    val subscription = Subscription(50.0, "Knæk Cancer", "213213", com.google.firebase.Timestamp.now())

    // Users
    override suspend fun addUser(user: User) {
        database.collection("Users").add(user).addOnSuccessListener { documentReference ->
            val documentId = documentReference.id
            Global.currentUser = documentId

            database.collection("Users").document(documentId).collection("Subscriptions")
                .add(subscription)
            database.collection("Users").document(documentId).collection("Donations")
                .add(subscription)
        }
    }

    override suspend fun getSubscriptions(userPath: String): List<Subscription> {
        val subscriptions = database.collection("Users").document(userPath).collection("Subscriptions")
        // Call method here
        val portfolio: List<Subscription> = subscriptions.get().await().toObjects()

        /*.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val documents = task.result
                    println(documents.documents)
                    documents!!.forEach { document ->
                        println("HELP US: " + document.getDouble("amount") +
                                " " + document.get("charityID"))
                        portfolio.add(
                                Portfolio(
                                        "Red Cross",
                                        25.0,
                                        document.getDouble("amount")!!,
                                        200.0
                                )
                        )
                    }
                }
            }*/
        return portfolio
    }

    override suspend fun deleteUser(userId: String) {
        database.collection("Users").document(userId).delete()
    }

    // Subscriptions
    override suspend fun addSubscription(amount: Double, user: String, charity: String) {
        val charityDocRef = database.collection("Charity").document(charity)
        val userDocRef = database.collection("User").document(user)

        val date = com.google.firebase.Timestamp.now()
        val id = System.currentTimeMillis().toString()

        database.runTransaction { transaction ->
            val charitySnapshot = transaction.get(charityDocRef)
            val charityID = charitySnapshot.getString("ID")!!
            val subscription = Subscription(amount, charityID, id, date)
            userDocRef.collection("Subscription").add(subscription)
        }
    }

    override suspend fun deleteSubscription(user: String, subscription: String) {
        database.collection("User").document(user).collection("Subscription").document(subscription)
            .delete()

    }

    override suspend fun modifySubscriptionAmount(
        user: String,
        subscription: String,
        amount: Double
    ) {
        val docRef = database.collection("User").document(user).collection("Subscription")
            .document(subscription)
        database.runTransaction { transaction ->
            transaction.update(docRef, "Amount", amount)
        }
    }

    // Donations
    override suspend fun addDonation(amount: Double, user: String, charity: String, Desc: String) {
        val charityDocRef = database.collection("Charity").document(charity)
        val userDocRef = database.collection("User").document(user)

        val date = com.google.firebase.Timestamp.now()
        val id = System.currentTimeMillis().toString()
        database.runTransaction { transaction ->
            val charitySnapshot = transaction.get(charityDocRef)
            val charityID = charitySnapshot.getString("ID")!!
            val charityName = charitySnapshot.getString("Name")!!
            val userSnapshot = transaction.get(userDocRef)
            val currency = userSnapshot.getString("Currency")!!
            val donation = Donation(amount, charityID, charityName, currency, date, Desc, id)
            database.collection("User").document(user).collection("Donation").add(donation)
        }
    }

    override suspend fun deleteDonation(user: String, donation: String) {
        database.collection("User").document(user).collection("Donations").document(donation)
            .delete()
    }

    // Charity
    override suspend fun increaseCharityDonationNumber(charity: String) {
        changeCharityField(charity, "Donations", 1)
    }

    override suspend fun decreaseCharityDonationNumber(charity: String) {
        changeCharityField(charity, "Donations", -1)
    }

    override suspend fun increaseCharityDonationNumber(amount: Int, charity: String) {
        changeCharityField(charity, "Donations", amount)
    }

    override suspend fun decreaseCharityDonationNumber(amount: Int, charity: String) {
        changeCharityField(charity, "Donations", -amount)
    }

    override suspend fun increaseCharityDonaterNumber(charity: String) {
        changeCharityField(charity, "Donators", 1)
    }

    override suspend fun decreaseCharityDonaterNumber(charity: String) {
        changeCharityField(charity, "Donators", -1)
    }

    override suspend fun increaseCharityDonaterNumber(amount: Int, charity: String) {
        changeCharityField(charity, "Donations", amount)
    }

    override suspend fun decreaseCharityDonaterNumber(amount: Int, charity: String) {
        changeCharityField(charity, "Donations", -amount)
    }

    //TODO we don't have a definition on what an admin is yet (is it a user variable, or a whitelist in the charity?)
    override suspend fun addCharityAdministator() {}
    override suspend fun deleteCharityAdministrator() {}

    override suspend fun addCharityArticle(articleContent: String, charity: String) {
        val articleId = System.currentTimeMillis().toString()
        val article = Article(articleId, articleContent)

        database.collection("Charity").document(charity).collection("Articles").add(article)
    }

    override suspend fun deleteArticle(article: String, charity: String) {
        database.collection("Charity").document(charity).collection("Article").document(article)
            .delete()
    }

    private fun changeCharityField(charity: String, fieldName: String, amount: Int) {
        val docRef = database.collection("Charity").document(charity)
        database.runTransaction { transaction ->
            val snapshot = transaction.get(docRef)
            val NewNumber = snapshot.getDouble(fieldName)!! + amount
            transaction.update(docRef, fieldName, NewNumber)
        }
    }
}