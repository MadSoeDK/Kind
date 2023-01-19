package com.example.kind.model

import com.google.firebase.Timestamp
import java.util.*

data class User (
    var name: String?,
    var monthlyPayment: Double? = null,
    var paymentMethod: String = "mobilepay"
)

data class Charity (
    var donaters: Int = 0,
    var donations: Int = 0,
    val id: String = "",
    val desc: String = "Check out this charity with the botton below",
    val iconImage: String = "",
    val mainImage: String = "",
    var name: String = "",
    val articles: List<Article> = listOf(),
    val inPortfolio: Boolean = false,
    val category: CharityCategory? = null
)

enum class CharityCategory(category: String) {
    All("All"),
    Health("Health"),
    Disasters("Disasters"),
    Climate("Climate"),
    Welfare("Welfare"),
    Children_Care("Children Care")
}

data class Article(
    val id: String = "",
    val title: String = "Sorry we can't reach this article right now",
    val paragraf: String = "Please come back later, we are working on the problem",
    val charityName: String = "",
    val iconImage: String = "",
    val mainImage: String = "",
    val author: String = "",
    val date: Timestamp = Timestamp.now()
)

data class Subscription(
    var amount: Double = 0.0,
    val charityID: String = "",
    val id: String = "",
    val initDate: Timestamp? = null,
    val charityName: String = ""
)

data class Donation(
    val amount: Double? = 0.0,
    val charityID: String? = "",
    val charityName: String? = "",
    val currency: String? = "",
    val date: Timestamp? = Timestamp(Date()),
    val description: String? = "",
    val ID: String? = ""
)

enum class DonationFrequency {
    Monthly,
    Quarterly,
    HalfYearly,
    Yearly
}

data class Payment (
    val amount: Double? = 0.0,
    val charity_id: String? = "Red barnet",
    val currency: String? = "",
    val date: Timestamp? = Timestamp(Date()),
)

data class KindPaymentIntent (
    val client_secret: String? = null,
    val customer: String? = null,
    val amount: Int? = null
)

