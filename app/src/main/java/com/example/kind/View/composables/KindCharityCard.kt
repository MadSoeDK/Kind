package com.example.kind.View.composables

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.kind.R
import com.example.kind.View.theme.Typography

@Composable
fun KindCharityCard(Title: String, Body: String, OrganizationIcon: Painter, ReadMore: String = "Read More", Category: String?){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .height(200.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp, 0.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.padding(0.dp, 0.dp)) {
                Image(
                    painter = OrganizationIcon, contentDescription = null, modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
            }
            Row(modifier = Modifier.padding(0.dp, 10.dp)) {
                Text(
                    text = Title,
                    fontWeight = Typography.headlineMedium.fontWeight,
                    fontSize = Typography.headlineMedium.fontSize,
                    color = Typography.headlineMedium.color)
            }
            Row(modifier = Modifier.padding(0.dp, 10.dp)) {
                //TODO need to center text
                Text(
                    text = Body,
                    fontWeight = Typography.displayMedium.fontWeight,
                    fontSize = Typography.displayMedium.fontSize,
                    color = Typography.displayMedium.color, )
            }
            Row(modifier = Modifier.padding(0.dp, 10.dp)) {
                //TODO Button works wierd and i've no clue why (it looks correct in the preview but when i change any code (even comments) it removes the text)
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = ReadMore,
                        fontWeight = Typography.headlineMedium.fontWeight,
                        fontSize = Typography.headlineMedium.fontSize,
                        color = Typography.headlineMedium.color)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KindCharityCardPreview(){
    val OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147)
    KindCharityCard(Title = "Red Cross", Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...", OrganizationIcon = OrganizationIcon, Category = null)
}