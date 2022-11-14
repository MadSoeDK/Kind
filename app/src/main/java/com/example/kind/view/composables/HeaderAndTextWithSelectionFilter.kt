package com.example.kind.view.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.View.theme.Typography

@Composable
fun HeaderAndTextWithSelectionFilter(Title: String, Subtitle: String?, Categories: Array<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp, 50.dp),
        verticalArrangement = Arrangement.Top, Alignment.Start
    ) {
        Row(
            modifier = Modifier.padding(0.dp, 10.dp)
        ) {
            Text(
                text = Title,
                fontWeight = Typography.headlineLarge.fontWeight,
                fontSize = Typography.headlineMedium.fontSize,
                color = Typography.headlineLarge.color
            )
        }
        if (Subtitle != null) {
            Row(modifier = Modifier.padding(0.dp, 10.dp)) {
                Text(
                    text = Subtitle,
                    fontWeight = Typography.headlineMedium.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    //TODO Need to find correct color
                    color = Typography.headlineMedium.color
                )
            }
        }
        Row(modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(0.dp, 10.dp)) {
            for (i in Categories.indices) {
                Text(
                    text = Categories[i] + "      ",
                    fontWeight = Typography.headlineMedium.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    //TODO Need to find correct color
                    color = Typography.headlineLarge.color
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharityHeaderAndSubsectionTextPreview() {
    HeaderAndTextWithSelectionFilter(
        Title = "Charity Explorer",
        Subtitle = "Get to know other charities better",
        Categories = arrayOf("Health", "Disasters", "Climate", "Welfare", "Children Care")
    )
}