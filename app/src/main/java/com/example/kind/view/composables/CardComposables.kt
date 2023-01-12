package com.example.kind.view.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kind.view.theme.Typography

@Composable
fun KindeCard(
    titleProvider: String,
    subTitleProvider: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .height(200.dp)
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .height(140.dp)
                //.padding(10.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Text(text = "Image")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
            ) {
                Box(
                    Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onTertiaryContainer)
                        .height(40.dp)
                        .width(40.dp)
                )
                CircleShape
                Column(modifier = Modifier.padding(8.dp, 0.dp)) {
                    Text(text = titleProvider, color = MaterialTheme.colorScheme.onBackground)
                    Text(text = subTitleProvider, color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}

@Composable
fun KindeNewsCard(
    Title: String,
    Body: String,
    OrganizationIcon: Painter,
    ReadMore: String = "Read More",
    Category: String,
    Subcategory: String
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(350.dp)
            .height(550.dp)
    ) {
        Row(modifier = Modifier.padding(0.dp, 5.dp))
        {
            Image(
                painter = OrganizationIcon,
                contentDescription = null,
                modifier = Modifier
                    .height(50.dp)
                    .width(90.dp)
                    .padding(20.dp, 0.dp)
            )
            Column()
            {
                Text(
                    textAlign = TextAlign.Center,
                    text = Category,
                    fontWeight = Typography.headlineMedium.fontWeight,
                    fontSize = Typography.displayMedium.fontSize,
                    color = Typography.headlineSmall.color
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = Subcategory,
                    fontWeight = Typography.headlineSmall.fontWeight,
                    fontSize = Typography.displayMedium.fontSize,
                    color = Typography.headlineSmall.color
                )
            }
        }
        Column(
            modifier = Modifier.padding(20.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(0.dp, 15.dp)) {
                Image(
                    painter = OrganizationIcon, contentDescription = null, modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }
            Row(modifier = Modifier.padding(0.dp, 2.dp)) {
                Text(
                    text = Title,
                    fontWeight = Typography.headlineMedium.fontWeight,
                    fontSize = Typography.displayMedium.fontSize,
                    color = Typography.headlineSmall.color,
                )
            }
            Row(
                modifier = Modifier
                    .padding(0.dp, 5.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                //TODO need to center text
                Text(
                    text = Body,
                    fontWeight = Typography.displayMedium.fontWeight,
                    fontSize = Typography.displayMedium.fontSize,
                    color = Typography.displayMedium.color,
                )
            }
            Row()
            {
                OutlinedButton(
                    modifier = Modifier
                        .padding(10.dp, 20.dp),
                    onClick = { /*TODO*/ },
                ) {
                    Text(
                        text = ReadMore,
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        color = Typography.headlineLarge.color
                    )
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(10.dp, 20.dp)
                        .background(Typography.headlineLarge.color),
                ) {
                    Text(
                        text = "Donate",
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        color = MaterialTheme.colorScheme.background
                    )
                }
            }
        }
    }
}

@Composable
fun KindeCharityCard(
    Title: String,
    Body: String,
    OrganizationIcon: Painter,
    ReadMore: String = "Read More",
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(250.dp)
            .height(250.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(0.dp, 5.dp)) {
                Image(
                    painter = OrganizationIcon,
                    contentDescription = null,
                    modifier = Modifier
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
            Row(modifier = Modifier.padding(0.dp, 5.dp)) {
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

@Composable
private fun CardEditor(
    @StringRes Title: Int,
    //Title: String
    subTitleProvider: String,
    @DrawableRes OrganizationIcon: Int,
    //OrganizationIcon: Painter,
    Body: String,
    cardColor: CardDefaults,
    ReadMore: String = "Read More",
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Card(
        colors = CardDefaults.cardColors()
    ) {
        /*Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Column(modifier = modifier.weight(1f)){
                Text(stringResource(title),
                    color = highlightColor,
                )


            }
        }*/
    }
}