package com.example.kind.View.composables
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.kind.R
import com.example.kind.View.theme.Typography
import com.example.kind.View.theme.background

@Composable
fun KindNewsCard(Title: String, Body: String, OrganizationIcon: Painter, ReadMore: String = "Read More", Category: String, Subcategory: String){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(350.dp)
            .height(550.dp)
    ) {
        Row(modifier = Modifier.padding(0.dp, 5.dp))
        {
            Image(
                painter = OrganizationIcon, contentDescription = null, modifier = Modifier
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
                    color = Typography.headlineSmall.color)
                Text(
                    textAlign = TextAlign.Center,
                    text = Subcategory,
                    fontWeight = Typography.headlineSmall.fontWeight,
                    fontSize = Typography.displayMedium.fontSize,
                    color = Typography.headlineSmall.color)
            }
        }
        Column(modifier = Modifier.padding(20.dp, 0.dp), horizontalAlignment = Alignment.CenterHorizontally) {
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
                    color = Typography.headlineSmall.color)
            }
            Row(modifier = Modifier.padding(0.dp, 5.dp), ) {
                //TODO need to center text
                Text(
                    text = Body,
                    fontWeight = Typography.displayMedium.fontWeight,
                    fontSize = Typography.displayMedium.fontSize,
                    color = Typography.displayMedium.color, )
            }
            Row()
            {
                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp, 20.dp)) {
                    Text(
                        text = ReadMore,
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        color = Typography.headlineLarge.color)
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(10.dp, 20.dp)
                        .background(Typography.headlineLarge.color)) {
                    Text(
                        text = "Donate",
                        fontWeight = Typography.labelLarge.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                        color = Color.White
                    )

                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun KindNewsCardPreview(){
    val OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147)
    KindNewsCard(
        Title = "Alle kender til krig, men ikke alle kender til krigens regler",
        Body = "Flere tusinde børn har i denne måned vist glæde af støttepakker fra red barnet",
        OrganizationIcon = OrganizationIcon,
        Category = "Krig",
        Subcategory = "War never changes"
    )
}
