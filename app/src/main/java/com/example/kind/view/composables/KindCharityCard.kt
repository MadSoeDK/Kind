package com.example.kind.view.composables

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.kind.R
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.paddingSize
import com.example.kind.view.theme.size

@Composable
fun KindCharityCard(
    Title: String,
    Body: String,
    OrganizationIcon: Painter,
    ReadMore: String = "Read More",
    Category: String?,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.paddingSize.xs)
            .width(MaterialTheme.size.xxl)
            .height(MaterialTheme.size.xxl)
    ) {

        Column(
            modifier = Modifier.padding(
                MaterialTheme.paddingSize.l,
                MaterialTheme.paddingSize.default,
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(
                MaterialTheme.paddingSize.default,
                MaterialTheme.paddingSize.xxxs,
            )) {
                Image(
                    painter = OrganizationIcon, contentDescription = null, modifier = Modifier
                        .height(MaterialTheme.size.xxs)
                        .width(MaterialTheme.size.xxs)
                )
            }
            Row(modifier = Modifier.padding(
                MaterialTheme.paddingSize.default,
                MaterialTheme.paddingSize.xxxs,
            )) {
                Text(
                    text = Title,
                    fontWeight = Typography.headlineMedium.fontWeight,
                    fontSize = Typography.headlineSmall.fontSize,
                    color = Typography.headlineLarge.color
                )
            }
            Row(modifier = Modifier.padding(
                MaterialTheme.paddingSize.default,
                MaterialTheme.paddingSize.xxxs,
            )) {
                //TODO need to center text
                Text(
                    text = Body,
                    fontWeight = Typography.displayMedium.fontWeight,
                    fontSize = Typography.displayMedium.fontSize,
                    color = Typography.displayMedium.color,
                )
            }
            OutlinedButton(onClick = { onClick() }) {
                Text(
                    text = ReadMore,
                    fontWeight = Typography.labelLarge.fontWeight,
                    fontSize = Typography.labelLarge.fontSize,
                    color = Typography.headlineLarge.color
                )
            }

        }
    }
}