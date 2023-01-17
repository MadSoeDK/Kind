package com.example.kind.model.service.impl

import android.util.Log
import com.example.kind.model.*
import com.example.kind.model.service.StorageService
import com.google.firebase.Timestamp
import com.google.firebase.auth.EmailAuthProvider
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Singleton

@Singleton
class StorageServiceImpl(
    var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
) : StorageService {
    // Users
    override suspend fun addUser(user: User) {
        Firebase.firestore.collection("Users").add(user).addOnSuccessListener { documentReference ->
            val documentId = currentUser?.uid.toString()

            Firebase.firestore.collection("Users").document("$documentId").collection("Subscriptions")
            Firebase.firestore.collection("Users").document("$documentId").collection("Donations")
        }
    }

    override suspend fun changeUser(user: User, uid : String) {
        Firebase.firestore.collection("Users").document(uid).set(user)
    }

    override suspend fun updateCurrentUser() {
        currentUser = FirebaseAuth.getInstance().currentUser
    }

    override suspend fun getUIDofCurrentUser(): String{
        return currentUser?.uid ?: ""
    }

    override suspend fun addToPortfolio(charityId: String){

        val checkList = getSubscriptions()
        var Subscribed = false

        // Check if you are already subscribed
        checkList.forEach {
            if (it.charityID == charityId) {
                Subscribed = true
            }
        }
        // If not, then add it to your subscriptions
        if (!Subscribed) {
            val subscription = Subscription(10.0, charityId, charityId, Timestamp(1, 1))

            Firebase.firestore.collection("Users").document(currentUser!!.uid).collection("Subscriptions")
                .add(subscription)
        }
    }

    override suspend fun removeFromPortfolio(charityId: String) {
        Firebase.firestore.collection("Users").document(currentUser!!.uid).collection("Subscriptions").whereEqualTo("charityID", charityId)
            .get().addOnSuccessListener { querySnapshot ->
                for (doc in querySnapshot) {
                    doc.reference.delete()
                }
            }.await()
        println("Removed from portfolio")
    }

    override suspend fun updateUser(email: String, password: String) {
        try {
            currentUser!!.updateEmail(email)
            currentUser!!.updatePassword(password)
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            currentUser!!.reauthenticate(
                EmailAuthProvider.getCredential(
                    currentUser?.email.toString(),
                    password
                )
            )
        }
    }

    override suspend fun getSubscriptions(): List<Subscription> {
        val documentId = currentUser?.uid.toString()
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

    override suspend fun deleteUser(confirmEmail: String, confirmPassword: String) {
        println("Profile " + currentUser.toString())
        try {
            Firebase.firestore.collection("Users").document(currentUser?.uid.toString()).delete()
            currentUser?.delete()
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            currentUser?.reauthenticate(
                EmailAuthProvider.getCredential(
                    currentUser!!.email.toString(),
                    confirmPassword
                )
            )
            Firebase.firestore.collection("Users").document(currentUser?.uid.toString()).delete()
            currentUser?.delete()
        }
    }

    // Subscriptions
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

    override suspend fun deleteSubscription(user: String, subscription: String) {
        Firebase.firestore.collection("User").document(user).collection("Subscription").document(subscription)
            .delete()
    }

    override suspend fun modifySubscriptionAmount(
        subscription: Subscription,
        amount: Double
    ) {
        val documentId = currentUser?.uid.toString()
        Firebase.firestore.collection("Users").document(documentId)
            .collection("Subscriptions").whereEqualTo("charityID", subscription.charityID).get()
            .addOnSuccessListener { documents ->
                documents.forEach {
                    val docRef: DocumentReference =
                        Firebase.firestore.collection("Users").document(currentUser?.uid.toString())
                            .collection("Subscriptions").document(it.id)
                    Firebase.firestore.runTransaction { transaction ->
                        transaction.update(docRef, "amount", amount)
                    }
                }
            }
    }

    // Donations
    override suspend fun addDonation(amount: Double, user: String, charity: String, Desc: String) {
        val charityDocRef = Firebase.firestore.collection("Charity").document(charity)
        val userDocRef = Firebase.firestore.collection("User").document(user)

        val date = com.google.firebase.Timestamp.now()
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

    // Charity
    override suspend fun getCharity(id: String): Charity? {

        //val charityList: List<Charity> = database.collection("Charity").whereEqualTo(FieldPath.documentId(), id).get().await().toObjects()

        try {
            return Firebase.firestore.collection("Charity").document(id).get().await().toObject()
        } catch (e: Exception) {
            return Charity(
                0,
                0,
                "",
                "Sorry, we are unable to find this charity page. Come back later"
            )
        }
    }

    override suspend fun getCharities(): List<Charity> {
        return Firebase.firestore.collection("Charity").get().await().toObjects()
    }

    override suspend fun getCharitiesByCategory(category: String): List<Charity> {
        return Firebase.firestore.collection("Charity").whereEqualTo("category", category).get().await().toObjects()
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
        return Firebase.firestore.collection("Articles").document(id).get().await().toObject()
    }

    override suspend fun getArticles(id: String): List<Article> {
        return Firebase.firestore.collection("Charity").document(id).collection("Articles").get().await()
            .toObjects()
    }

    override suspend fun getHomeArticles(id: String): List<Article> {
        // Setup vairables
        var subscriptions = listOf<Subscription>()
        var charities = mutableListOf<Charity?>()
        var articlePointers = listOf<String>()
        var articleList = mutableListOf<Article>()//database.collection("Articles").limit(5).get().await().toObjects()

        println(currentUser)
        // Get the subscriptions from User
        subscriptions =
            Firebase.firestore.collection("Users").document(currentUser!!.uid).collection("Subscriptions")
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

    override suspend fun addCharityArticle(articleContent: String, charity: String) {
        val articleId = System.currentTimeMillis().toString()
        val article = Article(articleId, articleContent)

        Firebase.firestore.collection("Charity").document(charity).collection("Articles").add(article)
    }

    override suspend fun deleteArticle(article: String, charity: String) {
        Firebase.firestore.collection("Charity").document(charity).collection("Article").document(article)
            .delete()
    }

    override suspend fun createStripePaymentIntent(amount: Double, currency: String, charityId: String): Task<DocumentReference> {
        val colRef = Firebase.firestore.collection("stripe_customers")
            .document(currentUser?.uid.toString())
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

    override suspend fun getClientSecret(doc: DocumentReference): String {
        val payment = Firebase.firestore.collection("stripe_customers")
            .document(currentUser?.uid.toString())
            .collection("payments")
            .document(doc.id).get().await().toObject<KindPaymentIntent>()!!

        return payment.client_secret!!
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