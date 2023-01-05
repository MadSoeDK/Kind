package com.example.kind.model.service.impl

import com.example.kind.model.Article
import com.example.kind.model.Donation
import com.example.kind.model.Subscription
import com.example.kind.model.User
import com.example.kind.model.service.StorageService
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp
import java.time.LocalDate
import java.util.Date

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
    override suspend fun addSubscription(amount : Double, user : String, charity : String){
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
    override suspend fun deleteSubscription(user : String, subscription : String){
        database.collection("User").document(user).collection("Subscription").document(subscription).delete()

    }
    override suspend fun modifySubscriptionAmount(user : String, subscription : String, amount : Double){
        val docRef = database.collection("User").document(user).collection("Subscription").document(subscription)
        database.runTransaction{ transaction ->
            transaction.update(docRef, "Amount", amount)
        }
    }

    // Donations
    override suspend fun addDonation(amount : Double, user : String, charity : String, Desc : String){
        val charityDocRef = database.collection("Charity").document(charity)
        val userDocRef = database.collection("User").document(user)

        val date = com.google.firebase.Timestamp.now()
        val id = System.currentTimeMillis().toString()
        database.runTransaction{ transaction ->
            val charitySnapshot = transaction.get(charityDocRef)
            val charityID = charitySnapshot.getString("ID")!!
            val charityName = charitySnapshot.getString("Name")!!
            val userSnapshot = transaction.get(userDocRef)
            val currency = userSnapshot.getString("Currency")!!
            val donation = Donation(amount, charityID, charityName, currency, date, Desc, id)
            database.collection("User").document(user).collection("Donation").add(donation)
        }
    }
    override suspend fun deleteDonation(user : String, donation : String){
        database.collection("User").document(user).collection("Donations").document(donation).delete()
    }

    // Charity
    override suspend fun increaseCharityDonationNumber(charity : String){
        changeCharityField(charity, "Donations", 1)
    }
    override suspend fun decreaseCharityDonationNumber(charity : String){
        changeCharityField(charity, "Donations", -1)
    }
    override suspend fun increaseCharityDonationNumber(amount :Int, charity : String){
        changeCharityField(charity, "Donations", amount)
    }
    override suspend fun decreaseCharityDonationNumber(amount :Int, charity : String){
        changeCharityField(charity, "Donations", -amount)
    }
    override suspend fun increaseCharityDonaterNumber(charity : String){
        changeCharityField(charity, "Donators", 1)
    }
    override suspend fun decreaseCharityDonaterNumber(charity : String){
        changeCharityField(charity, "Donators", -1)
    }
    override suspend fun increaseCharityDonaterNumber(amount :Int, charity : String){
        changeCharityField(charity, "Donations", amount)
    }
    override suspend fun decreaseCharityDonaterNumber(amount :Int, charity : String){
        changeCharityField(charity, "Donations", -amount)
    }

    //TODO we don't have a definition on what an admin is yet (is it a user variable, or a whitelist in the charity?)
    override suspend fun addCharityAdministator(){}
    override suspend fun deleteCharityAdministrator(){}

    override suspend fun addCharityArticle(articleContent : String, charity : String){
        val articleId = System.currentTimeMillis().toString()
        val article = Article(articleId, articleContent)

        database.collection("Charity").document(charity).collection("Articles").add(article)
    }
    override suspend fun deleteArticle(article : String, charity : String){
        database.collection("Charity").document(charity).collection("Article").document(article).delete()
    }

    private fun changeCharityField(charity : String, fieldName : String, amount : Int){
        val docRef = database.collection("Charity").document(charity)
        database.runTransaction{ transaction ->
            val snapshot = transaction.get(docRef)
            val NewNumber = snapshot.getDouble(fieldName)!! + amount
            transaction.update(docRef, fieldName, NewNumber)
        }
    }
}