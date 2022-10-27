package com.example.kind.View.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> CardListHorizontalScroll(listOfContent: List<T>) {
    Row (
        modifier =  Modifier.horizontalScroll(rememberScrollState())
    ){
        for (card in listOfContent) {
            KindCard(titleProvider = "Some organization", subTitleProvier = card.toString())
        }
    }
}