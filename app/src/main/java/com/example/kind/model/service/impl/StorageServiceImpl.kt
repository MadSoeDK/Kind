package com.example.kind.model.service.impl

import android.util.Log
import com.example.kind.model.*
import com.example.kind.model.service.StorageService
import com.google.firebase.Timestamp
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Singleton

@Singleton
class StorageServiceImpl: StorageService {

    // Users
    override suspend fun addUser(user: User) {
        Firebase.firestore.collection("Users").add(user).addOnSuccessListener { documentReference ->
            val documentId = Firebase.auth.currentUser?.uid.toString()

            Firebase.firestore.collection("Users").document("$documentId").collection("Subscriptions")
            Firebase.firestore.collection("Users").document("$documentId").collection("Donations")
        }
    }

    /**
     * Adds a subscription to a user in firebase
     */
    override suspend fun addSubscriptionToUser(subs: List<Subscription>) {
        subs.forEach {
            Firebase.firestore.collection("Users").document(Firebase.auth.uid!!).collection("Subscriptions").add(it)
        }
    }

    /**
     * Deletes a user from the firestore
     */
    override suspend fun deleteUserFromFirestore(password: String) {
        try {
            Firebase.firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString()).delete().await()
            Firebase.auth.currentUser?.delete()?.await()
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            throw e
        }
    }

    /**
     * Adds a charity to a user's portfolio
     */
    override suspend fun addCharityToPortfolio(charityId: String) {
        val checkList = getSubscriptions()
        var Subscribed = false
        var charity: Charity? = Charity()

        // Check if you are already subscribed
        checkList.forEach {
            if (it.charityID == charityId) {
                Subscribed = true
            }
        }

        //Thread.sleep(1000)

        // If not, then add it to your subscriptions
        if (!Subscribed) {
            charity = getCharity(charityId)
            val subscription = Subscription(10.0, charityId, charityId, Timestamp(1, 1), charity!!.name)

            Firebase.firestore.collection("Users").document(Firebase.auth.currentUser!!.uid).collection("Subscriptions")
                .add(subscription)
        }
    }

    /**
     * Removes a charity from a user's portfolio
     */
    override suspend fun removeCharityFromPortfolio(charityId: String) {
        Firebase.firestore.collection("Users").document(Firebase.auth.currentUser!!.uid).collection("Subscriptions").whereEqualTo("charityID", charityId)
            .get().addOnSuccessListener { querySnapshot ->
                for (doc in querySnapshot) {
                    doc.reference.delete()
                }
            }.await()
    }

    /**
     * Update user's Email and password
     */
    override suspend fun updateUser(email: String, password: String) {
        Firebase.auth.currentUser!!.updateEmail(email)
        Firebase.auth.currentUser!!.updatePassword(password)
    }

    /**
     * Retrieves all the user's subscriptions that a user has
     */
    override suspend fun getSubscriptions(): List<Subscription> {
        val documentId = Firebase.auth.currentUser?.uid.toString()
        val subscriptions =
            Firebase.firestore.collection("Users").document(documentId).collection("Subscriptions")
        // Call method here
        var portfolio: List<Subscription> = ArrayList()
        try {
            portfolio = subscriptions.get().await().toObjects(Subscription::class.java)
        } catch (e: Exception) {
            println(e.printStackTrace())
        }
        return portfolio
    }

    override suspend fun getDonationsAmount(): Int{
        val donationList = Firebase.firestore.collection("stripe_customers").document(Firebase.auth.currentUser!!.uid)
                            .collection("payments").get().await().toObjects<Payment>()

        return donationList.sumOf {
            it.amount!!.toInt()/100
        }
    }

    /**
     * Adds a subscription to a user in the firestore
     */
    override suspend fun addSubscription(amount: Double, user: String, charity: String) {
        val charityDocRef = Firebase.firestore.collection("Charity").document(charity)
        val userDocRef = Firebase.firestore.collection("User").document(user)

        val date = Timestamp.now()
        val id = UUID.randomUUID().toString()

        Firebase.firestore.runTransaction { transaction ->
            val charitySnapshot = transaction.get(charityDocRef)
            val charityID = charitySnapshot.getString("ID")!!
            val subscription = Subscription(amount, charityID, id, date)
            userDocRef.collection("Subscription").add(subscription)
        }
    }

    /**
     * Deletes a subscription given a specific user
     */
    override suspend fun deleteSubscription(user: String, subscription: String) {
        Firebase.firestore.collection("User").document(user).collection("Subscription").document(subscription)
            .delete()
    }

    /**
     * Modifies a user's subscription amount
     */
    override suspend fun modifySubscriptionAmount(
        subscription: Subscription,
        amount: Double
    ) {
        val documentId = Firebase.auth.currentUser?.uid.toString()
        Firebase.firestore.collection("Users").document(documentId)
            .collection("Subscriptions").whereEqualTo("charityID", subscription.charityID).get()
            .addOnSuccessListener { documents ->
                documents.forEach {
                    val docRef: DocumentReference =
                        Firebase.firestore.collection("Users").document(Firebase.auth.currentUser?.uid.toString())
                            .collection("Subscriptions").document(it.id)
                    Firebase.firestore.runTransaction { transaction ->
                        transaction.update(docRef, "amount", amount)
                    }
                }
            }
    }

    /**
     * Adds a donation a user has made
     */
    override suspend fun addDonation(amount: Double, user: String, charity: String, Desc: String) {
        val charityDocRef = Firebase.firestore.collection("Charity").document(charity)
        val userDocRef = Firebase.firestore.collection("User").document(user)

        val date = Timestamp.now()
        val id = UUID.randomUUID().toString()
        Firebase.firestore.runTransaction { transaction ->
            val charitySnapshot = transaction.get(charityDocRef)
            val charityID = charitySnapshot.getString("ID")!!
            val charityName = charitySnapshot.getString("Name")!!
            val userSnapshot = transaction.get(userDocRef)
            val currency = userSnapshot.getString("Currency")!!
            val donation = Donation(amount, charityID, charityName, currency, date, Desc, id)
            Firebase.firestore.collection("User").document(user).collection("Donation").add(donation)
        }
    }

    override suspend fun deleteDonation(user: String, donation: String) {
        Firebase.firestore.collection("User").document(user).collection("Donations").document(donation)
            .delete()
    }

    override suspend fun getDonations(user: String): List<Donation> {
        return Firebase.firestore.collection("User").document(user).collection("payments").get().await()
            .toObjects()
    }

    override suspend fun getPayments(user: String): List<Payment> {
        return Firebase.firestore.collection("stripe_customers").document(user).collection("payments").limit(10).get().await()
            .toObjects()
    }

    // Charity
    override suspend fun getCharity(id: String): Charity? {
        return Firebase.firestore.collection("Charity").document(id).get().await().toObject()
    }

    override suspend fun getCharities(): List<Charity> {
        return Firebase.firestore.collection("Charity").get().await().toObjects()
    }

    override suspend fun getCharitiesByCategory(category: String): List<Charity> {
        return Firebase.firestore.collection("Charity").whereEqualTo("category", category).get().await().toObjects()
    }

    override suspend fun getArticle(id: String): Article? {
        return Firebase.firestore.collection("Articles").document(id).get().await().toObject()
    }

    override suspend fun getArticles(id: String): List<Article> {
        return Firebase.firestore.collection("Charity").document(id).collection("Articles").get().await()
            .toObjects()
    }

    /**
     * Gets the articles from charities a user is subscribed to
     */
    override suspend fun getHomeArticles(id: String): List<Article> {
        // Setup vairables
        val subscriptions: List<Subscription>
        val charities = mutableListOf<Charity?>()
        val articleList = mutableListOf<Article>()

        // Get the subscriptions from User
        subscriptions =
            Firebase.firestore.collection("Users").document(Firebase.auth.currentUser!!.uid).collection("Subscriptions")
                .get().await().toObjects()


        // Get charities from subscription
        subscriptions.forEach() {
            charities.add(Firebase.firestore.collection("Charity").document(it.charityID).get().await()
                .toObject())
        }

        // Get article pointers from charities
        charities.forEach {
            articleList.addAll(Firebase.firestore.collection("Charity").document(it!!.id).collection("Articles")
                .get().await().toObjects())
        }

        // article

        return articleList
    }

    /**
     * Adds a article to a specific charity
     */
    override suspend fun addCharityArticle(articleContent: String, charity: String) {
        val articleId = System.currentTimeMillis().toString()
        val article = Article(articleId, articleContent)

        Firebase.firestore.collection("Charity").document(charity).collection("Articles").add(article)
    }

    /**
     * Deletes a article from a specfic charity
     */
    override suspend fun deleteArticle(article: String, charity: String) {
        Firebase.firestore.collection("Charity").document(charity).collection("Article").document(article)
            .delete()
    }

    /**
     * Create s a stripe payment intent that can be confirmed og cancelled and adds it to
     * a user's payments in the stripe_customers collection
     */
    override suspend fun createStripePaymentIntent(amount: Double, currency: String, charityId: String): Task<DocumentReference> {
        val colRef = Firebase.firestore.collection("stripe_customers")
            .document(Firebase.auth.currentUser?.uid.toString())
            .collection("payments")

        val docRef = colRef.add(
            hashMapOf(
                "amount" to amount,
                "currency" to currency,
                "date" to Date(),
                "charity_id" to charityId
            )
        ).addOnSuccessListener {
            Log.d("payment", "Added document to firebase with ID ${it.id}")
        }

        return docRef
    }

    /**
     * Fetches a client secret to confirm a payment intent
     */
    override suspend fun getClientSecret(doc: DocumentReference): String {
        val payment = Firebase.firestore.collection("stripe_customers")
            .document(Firebase.auth.currentUser?.uid.toString())
            .collection("payments")
            .document(doc.id).get().await().toObject<KindPaymentIntent>()!!

        if (payment.client_secret == null)
            throw Exception("Client secret is null")

        return payment.client_secret
    }

    private fun changeCharityField(charity: String, fieldName: String, amount: Int) {
        val docRef = Firebase.firestore.collection("Charity").document(charity)
        Firebase.firestore.runTransaction { transaction ->
            val snapshot = transaction.get(docRef)
            val NewNumber = snapshot.getDouble(fieldName)!! + amount
            transaction.update(docRef, fieldName, NewNumber)
        }
    }
}