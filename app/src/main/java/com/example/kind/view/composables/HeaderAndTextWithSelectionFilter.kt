package com.example.kind.view.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.paddingSize

@Composable
fun HeaderAndTextWithSelectionFilter(Title: String, Subtitle: String?, Categories: Array<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.paddingSize.l, MaterialTheme.paddingSize.xl),
        verticalArrangement = Arrangement.Top, Alignment.Start
    ) {
        Row(
            modifier = Modifier.padding(
                MaterialTheme.paddingSize.default,
                MaterialTheme.paddingSize.m,
            )
        ) {
            Text(
                text = Title,
                fontWeight = Typography.headlineLarge.fontWeight,
                fontSize = Typography.headlineMedium.fontSize,
                color = Typography.headlineLarge.color
            )
        }
        if (Subtitle != null) {
            Row(modifier = Modifier.padding(
                MaterialTheme.paddingSize.default,
                MaterialTheme.paddingSize.m,
            )) {
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
            .padding(
                MaterialTheme.paddingSize.default,
                MaterialTheme.paddingSize.m,
            )) {
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
fun CharityHeaderAndSelectionPreview() {
    HeaderAndTextWithSelectionFilter(
        Title = "Charity Explorer",
        Subtitle = "Get to know other charities better",
        Categories = arrayOf("Health", "Disasters", "Climate", "Welfare", "Children Care")
    )
}