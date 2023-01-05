package com.example.kind.model.service.impl

import com.example.kind.model.Article
import com.example.kind.model.Donation
import com.example.kind.model.User
import com.example.kind.model.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp
import java.time.LocalDate
import java.util.Date

class StorageServiceImpl : StorageService {
    private val database = Firebase.firestore

    // Users
    override suspend fun addUser(email: String, password: String){

        val userId = System.currentTimeMillis().toString()
        val user = User(userId,"John", email,password,0, 20.0)

        database.collection("Users").add(user)

        println("THIS IS OUR USERS: " + FirebaseFirestore.getInstance().collection("Users").path)

    }
    override suspend fun deleteUser(){}

    // Subscriptions
    override suspend fun addSubscription(){}
    override suspend fun deleteSubscription(){}
    override suspend fun modifySubscriptionAmount(){}
    override suspend fun modifySubscriptionPlan(){}

    // Donations
    override suspend fun addDonation(){}
    override suspend fun deleteDonation(){}

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