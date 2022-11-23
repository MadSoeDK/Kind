package com.example.kind.view.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
//import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.paddingSize

@Composable
fun CharityHeaderAndSubsectionText(Title: String, Subtitle: String, Categories: Array<String>){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            paddingSize.xl,
            paddingSize.xxxxl),
        verticalArrangement = Arrangement.Top, Alignment.Start) {
        Row (
            modifier = Modifier.padding(
                paddingSize.default,
                paddingSize.s)
        ){
            Text(
                text = Title,
                fontWeight = Typography.headlineLarge.fontWeight,
                fontSize = Typography.headlineSmall.fontSize,
                color = Typography.headlineLarge.color
            )
        }
        Row(modifier = Modifier.padding(
            paddingSize.default,
            paddingSize.s)) {
            Text(
                text = Subtitle,
                fontWeight = Typography.headlineMedium.fontWeight,
                fontSize = Typography.headlineSmall.fontSize,
                //TODO Need to find correct color
                color = Typography.headlineMedium.color)
        }
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            for (i in Categories.indices){
            Text(
                text = Categories[i] + "      ",
                fontWeight = Typography.headlineMedium.fontWeight,
                fontSize = Typography.labelLarge.fontSize,
                //TODO highlight needs to be primary color and not subheading
                color = Typography.headlineMedium.color)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharityHeaderAndSubsectionTextPreview() {
    CharityHeaderAndSubsectionText(Title = "Charity Explorer", Subtitle = "Get to know other charities better", Categories = arrayOf("Health", "Disasters", "Climate", "Welfare", "Children Care"))
}