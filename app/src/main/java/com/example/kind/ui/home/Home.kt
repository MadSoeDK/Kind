package com.example.kind.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.R
import androidx.lifecycle.ViewModel
import com.example.kind.ui.theme.Shapes
import com.example.kind.ui.theme.Typography

class HomeViewModel : ViewModel() {
    // Logic etc...
    fun getText(): String {
        return "Du er blandt top 5% af donorer. Godt gået!"
    }

    fun getDonatedAmount(): String {
        return 1534.toString() + " kr."
    }

    fun getArticles(): List<String> {
        val articles = ArrayList<String>()
        articles.add("Article 1")
        articles.add("Article 2")
        return articles
    }
}

@Composable
fun HomeScreen(
    donatedAmountProvider: String,
    welcomeText: String,
    articles: List<String>,
) {
    HeaderAndText(donatedAmountProvider, welcomeText)
    Card (
        shape = Shapes.medium,
        border = BorderStroke(1.dp, Color.Black)
    ){
        Column (
            modifier = Modifier.padding(10.dp),
        ){
            Row {
                Text(text = "Title")
            }
            Row {
                Text(text = "Subtitle")
            }
        }
    }
}

@Composable
fun HeaderAndText(
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