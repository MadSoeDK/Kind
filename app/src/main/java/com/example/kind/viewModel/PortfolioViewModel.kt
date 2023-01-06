package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.kind.Global
import com.example.kind.model.Portfolio
import com.example.kind.model.User
import com.example.kind.model.service.impl.StorageServiceImpl
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class PortfolioViewModel : ViewModel() {

    var storage: StorageServiceImpl = StorageServiceImpl()

    var formState by mutableStateOf(FormState())

    var isOpen by mutableStateOf(false)

    var fields: List<KindTextField> = listOf(
        KindTextField(
            name = "Indtast beløb",
            label = "Indtast beløb",
            validators = listOf(Required())
        ),
    )

    fun toggleModal() {
        isOpen = !isOpen
    }

    fun onFormSubmit() {
        if (formState.validate()) {
            // TODO: Do something on form submission
        }
        //TODO: Add alert for user
        println("Form submission error!")
    }

    fun getMonthlyDonatedAmount(): String {
        var amount = 300
        return amount.toString()
    }

    fun getPortfolioDonation(): List<Portfolio> {
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        return coroutineScope.getPortfolio()
    }

    fun CoroutineScope.getPortfolio(): List<Portfolio> {
        var portfolio: MutableList<Portfolio> = mutableListOf()
        launch(Dispatchers.IO) {
            // Call method here
            val subscription = storage.getSubscriptions(Global.currentUser)
            subscription.get().addOnCompleteListener { task ->
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
            }
        }
        return portfolio
    }

    fun getPercentages(): List<Float> {

        fun CoroutineScope.getPercentages() {
            launch(Dispatchers.IO) {
                // Call method here
                val subscription = storage.getSubscriptions(Global.currentUser)
                subscription.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val documents = task.result
                        documents!!.forEach { document ->
                            var amounts: ArrayList<Double> = ArrayList()

                        }
                    }

                }
            }
        }

        val percentages: MutableList<Float> = mutableListOf()
        /*for (i in getPortfolioDonation()) {
            percentages.add(25f)
        }*/

        return percentages
    }

    /*fun getSpend(): Float {
        var spend = 0f
        for (i in getPortfolioDonation()) {
            spend += 25f
        }
        return spend
    }*/

    fun getColors(): List<Color> {
        val colors: MutableList<Color> = mutableListOf()
        colors.add(Color(0xFFbf95d4))
        colors.add(Color(0xFFf4ac1a))
        colors.add(Color(0xFF8b0a50))
        colors.add(Color(0xFFeb4034))

        colors.add(Color(0xFF27b0e6))
        colors.add(Color(0xFFa6e007))

        colors.add(Color(0xFFFF001D))
        colors.add(Color(0xFF07E082))
        colors.add(Color(0xFFA90AFF))
        colors.add(Color(0xFFFFE200))

        var availableColors: MutableList<Color> = mutableListOf()
        var j = 0
        /*for (i in getPortfolioDonation()) {
            availableColors.add(colors.get(j))
            j = j + 1
        }*/
        return availableColors
    }

}