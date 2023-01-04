package com.example.kind.view.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.view.theme.Typography

@Composable
fun CharityHeaderAndSubsectionText(Title: String, Subtitle: String, Categories: Array<String>){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(25.dp, 50.dp),
        verticalArrangement = Arrangement.Top, Alignment.Start) {
        Row (
            modifier = Modifier.padding(0.dp, 10.dp)
        ){
            Text(
                text = Title,
                fontWeight = Typography.headlineLarge.fontWeight,
                fontSize = Typography.headlineSmall.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(modifier = Modifier.padding(0.dp, 10.dp)) {
            Text(
                text = Subtitle,
                fontWeight = Typography.headlineMedium.fontWeight,
                fontSize = Typography.headlineSmall.fontSize,
                //TODO Need to find correct color
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            for (i in Categories.indices){
            Text(
                text = Categories[i] + "      ",
                fontWeight = Typography.headlineMedium.fontWeight,
                fontSize = Typography.labelLarge.fontSize,
                //TODO Need to find correct color
                color = MaterialTheme.colorScheme.primary
            )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharityHeaderAndSubsectionTextPreview() {
    CharityHeaderAndSubsectionText(Title = "Charity Explorer", Subtitle = "Get to know other charities better", Categories = arrayOf("Health", "Disasters", "Climate", "Welfare", "Children Care"))
}