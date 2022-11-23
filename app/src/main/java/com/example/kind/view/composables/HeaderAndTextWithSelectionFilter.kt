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
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.paddingSize

@Composable
fun HeaderAndTextWithSelectionFilter(Title: String, Subtitle: String?, Categories: Array<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingSize.xl, paddingSize.xxxxl),
        verticalArrangement = Arrangement.Top, Alignment.Start
    ) {
        Row(
            modifier = Modifier.padding(
                paddingSize.default,
                paddingSize.s,
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
                paddingSize.default,
                paddingSize.s,
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
                paddingSize.default,
                paddingSize.s,
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