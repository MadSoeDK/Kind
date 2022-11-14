package com.example.kind.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.kind.model.Portfolio
import com.example.kind.view.composables.FormState
import com.example.kind.view.composables.KindTextField
import com.example.kind.view.composables.Required

class PortfolioViewModel : ViewModel() {

    var formState by mutableStateOf(FormState())

    var fields: List<KindTextField> = listOf(
        KindTextField(name = "Indtast beløb", label = "Indtast beløb", validators = listOf(Required())),
    )

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
    fun getPortfolioDonation() : List<Portfolio> {
        return listOf(
            Portfolio("Røde Kors", 5f, 100f, 150f),
            Portfolio("Støt Cancer", 50f, 100f, 250f),
            Portfolio("UNICEF", 10f, 100f, 250f),
            Portfolio("Mødrehjælpen", 5f, 101f, 540f),
            Portfolio("Julehjælpen", 12f, 100f, 125f),
            Portfolio("Diabetesforeningen", 10f, 100f, 125f),
            Portfolio("Hjemløsefonden", 3f, 100f, 125f),
            Portfolio("Demensforeningen", 5f, 100f, 125f)

        )
    }
    fun getPercentages() : List<Float> {
        val percentages : MutableList<Float> = mutableListOf()
        for (i in getPortfolioDonation()) {
            percentages.add(i.pct)
        }

        return percentages
    }
    fun getColors() : List<Color> {
        val colors : MutableList<Color> = mutableListOf()
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

        var availableColors : MutableList<Color> = mutableListOf()
        var j  = 0
        for (i in getPortfolioDonation()) {
            availableColors.add(colors.get(j))
            j = j + 1
        }
        return availableColors
    }

}