package com.example.kind.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.kind.ui.theme.Typography

class HomeViewModel : ViewModel() {
    // Logic etc...
    fun getText(): String {
        return "Du er blandt top 5% af donorer. Godt gået!"
    }

    fun getDonatedAmount(): String {
        return 1534.toString() + " kr."
    }
}

@Composable
fun HomeScreen(
    donatedAmountProvider: String,
    welcomeText: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.padding(0.dp, 10.dp)
        ){
            Text(
                text = donatedAmountProvider,
                fontWeight = Typography.h1.fontWeight,
                fontSize = Typography.h1.fontSize,
            )
        }
        Row {
            Text(text = welcomeText)
        }
    }
}