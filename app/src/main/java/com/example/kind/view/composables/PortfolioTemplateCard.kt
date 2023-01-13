package com.example.kind.view.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.kind.R
import com.example.kind.view.theme.Typography

@Composable
fun PortfolioTemplateCard(
    Title: String,
    Body: String,
    OrganizationIcon: Painter,
    ReadMore: String = "Read More ?",
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp)
            .height(250.dp),
        border = BorderStroke(
            2.dp,
            color = if (isSystemInDarkTheme()) {
                MaterialTheme.colorScheme.primary} else {
                    MaterialTheme.colorScheme.outline})
    ) {

        Column(
            modifier = Modifier.padding(20.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(0.dp, 5.dp)) {
                Image(
                    painter = OrganizationIcon, contentDescription = null, modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
            }
            Row(modifier = Modifier.padding(0.dp, 5.dp)) {
                Text(
                    text = Title,
                    fontWeight = Typography.headlineMedium.fontWeight,
                    fontSize = Typography.headlineSmall.fontSize,
                    color = Typography.headlineLarge.color
                )
            }
            Row(modifier = Modifier.padding(0.dp, 15.dp)) {
                //TODO need to center text
                Text(
                    text = Body,
                    fontWeight = Typography.displayMedium.fontWeight,
                    fontSize = Typography.displayMedium.fontSize,
                    color = Typography.displayMedium.color,
                )
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = ReadMore,
                    fontWeight = Typography.labelLarge.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PortfolioTemplateCardPreview() {
    val OrganizationIcon = painterResource(id = R.drawable.bekindsplashart1)
    PortfolioTemplateCard(
        Title = "Red Cross",
        Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
        OrganizationIcon = OrganizationIcon,
    )
}