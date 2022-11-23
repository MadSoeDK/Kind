package com.example.kind.view.composables

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.R
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.fontSize
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
            .padding(paddingSize.s) //½ af figma
            .width(size.xxl)
            .height(size.xxl)
    ) {

        Column(
            modifier = Modifier.padding(
                paddingSize.l,
                paddingSize.default,
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(
                paddingSize.default,
                paddingSize.xxxs,
            )) {
                Image(
                    painter = OrganizationIcon, contentDescription = null, modifier = Modifier
                        .height(size.xxs)
                        .width(size.xxs)
                )
            }
            Row(modifier = Modifier.padding(
                paddingSize.default,
                paddingSize.xxxs,
            )) {
                Text(
                    text = Title,
                    fontWeight = Typography.headlineMedium.fontWeight,
                    fontSize = fontSize.subheader,
                    color = Color.Black
                )
            }
            Row(modifier = Modifier.padding(
                paddingSize.default,
                paddingSize.xxxs,
            )) {
                //TODO need to center text
                Text(
                    text = Body,
                    fontWeight = Typography.displayMedium.fontWeight,
                    fontSize = fontSize.body,
                    color = Color.Black,
                )
            }
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(
                    text = ReadMore,
                    fontWeight = Typography.labelLarge.fontWeight,
                    fontSize = fontSize.buttonText,
                    color = Typography.headlineLarge.color,
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun KindCharityCardPreview() {
    val OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147)
    KindCharityCard(
        Title = "Red Cross",
        Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
        OrganizationIcon = OrganizationIcon,
        Category = null,
        onClick = (viewModel()),
    )
}