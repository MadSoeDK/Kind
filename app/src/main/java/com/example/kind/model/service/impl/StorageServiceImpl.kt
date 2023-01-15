package com.example.kind.model.service.impl

import android.util.Log
import com.example.kind.model.*
import com.example.kind.model.User
import com.example.kind.model.service.StorageService

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.stripe.android.Stripe
import kotlinx.coroutines.tasks.await
import okhttp3.Request
import java.util.UUID
import javax.inject.Singleton

@Singleton
class StorageServiceImpl(
    private val currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
) : StorageService {
    private val database = Firebase.firestore
     //= FirebaseAuth.getInstance().currentUser

    val subscription = Subscription(50.0, "Knæk Cancer", "213213", com.google.firebase.Timestamp.now())

    // Users
    override suspend fun addUser(user: User) {
        database.collection("Users").add(user).addOnSuccessListener { documentReference ->
            val documentId = currentUser?.uid.toString()

            database.collection("Users").document("$documentId").collection("Subscriptions")
                .add(subscription)
            database.collection("Users").document("$documentId").collection("Donations")
                .add(subscription)
        }
    }

    override suspend fun getSubscriptions(): List<Subscription> {
        val documentId = currentUser?.uid.toString()
        val subscriptions =
            database.collection("Users").document(documentId).collection("Subscriptions")
        // Call method here
        var portfolio: List<Subscription> = ArrayList()
        try {
            portfolio = subscriptions.get().await().toObjects(Subscription::class.java)
        } catch (e: Exception) {
            println(e.printStackTrace())
        }
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
        val id = UUID.randomUUID().toString()

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
        subscription: Subscription,
        amount: Double
    ) {
        val documentId = currentUser?.uid.toString()
        database.collection("Users").document(documentId)
            .collection("Subscriptions").whereEqualTo("charityID", subscription.charityID).get().addOnSuccessListener { documents ->
                documents.forEach {
                    val docRef: DocumentReference =
                        database.collection("Users").document(currentUser?.uid.toString())
                            .collection("Subscriptions").document(it.id)
                    database.runTransaction { transaction ->
                        transaction.update(docRef, "amount", amount)
                    }
                }
            }
    }

    // Donations
    override suspend fun addDonation(amount: Double, user: String, charity: String, Desc: String) {
        val charityDocRef = database.collection("Charity").document(charity)
        val userDocRef = database.collection("User").document(user)

        val date = com.google.firebase.Timestamp.now()
        val id = UUID.randomUUID().toString()
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
    override suspend fun getCharity(id: String): Charity?{

        //val charityList: List<Charity> = database.collection("Charity").whereEqualTo(FieldPath.documentId(), id).get().await().toObjects()

        try {
            return database.collection("Charity").document(id).get().await().toObject()
        }
        catch (e: Exception)
        {
            return Charity(0,0,"","Sorry, we are unable to find this charity page. Come back later")
        }
    }

    override suspend fun getCharities(): List<Charity> {
        return database.collection("Charity").get().await().toObjects()
    }

    override suspend fun increaseCharityDonationNumber(charity: String) {
        changeCharityField(charity, "Donations", 1)
    }

    override suspend fun decreaseCharityDonationNumber(charity: String) {
        changeCharityField(charity, "Donations", -1)
    }

    override suspend fun increaseCharityDonaterNumber(charity: String) {
        changeCharityField(charity, "Donators", 1)
    }

    override suspend fun decreaseCharityDonaterNumber(charity: String) {
        changeCharityField(charity, "Donators", -1)
    }


    //TODO we don't have a definition on what an admin is yet (is it a user variable, or a whitelist in the charity?)
    override suspend fun addCharityAdministator() {}

    override suspend fun deleteCharityAdministrator() {}

    override suspend fun getArticle(id: String): Article? {
        return database.collection("Articles").document(id).get().await().toObject()
    }

    override suspend fun getArticles(id: String): List<Article> {
        return database.collection("Charity").document(id).collection("Articles").get().await()
            .toObjects()
    }

    override suspend fun getHomeArticles(id: String): List<Article> {
        return database.collection("Articles").limit(5).get().await().toObjects()
    }

    override suspend fun addCharityArticle(articleContent: String, charity: String) {
        val articleId = System.currentTimeMillis().toString()
        val article = Article(articleId, articleContent)

        database.collection("Charity").document(charity).collection("Articles").add(article)
    }

    override suspend fun deleteArticle(article: String, charity: String) {
        database.collection("Charity").document(charity).collection("Article").document(article)
            .delete()
    }

    override suspend fun createStripePaymentIntent(amount: Double, currency: String): String {
        //database.useEmulator("10.0.2.2", 8080)
        var client_secret = ""
        database.collection("stripe_customers")
            .document(currentUser?.uid.toString())
            .collection("payments")
            .add(
                hashMapOf(
                    "amount" to amount,
                    "currency" to currency
                )
            ).addOnSuccessListener {
                Log.d("payment", "Added document to firebase with ID ${it.id}")

                it.addSnapshotListener { snapshot, e ->
                    if(e != null) {
                        println(e.printStackTrace())
                        return@addSnapshotListener
                    }
                    if ((snapshot != null) && snapshot.exists()) {
                        client_secret = snapshot.data?.get("client_secret").toString()
                    }
                }

            }
        println("Client secret :$client_secret")
        return client_secret
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