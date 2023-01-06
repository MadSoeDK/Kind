package com.example.kind.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.view.theme.Typography
import com.example.kind.viewModel.ArticleViewModel

@Composable
fun ArticleScreen(
    viewModel: ArticleViewModel
)
{
    IconButton(onClick = {viewModel.navController.popBackStack()}) {
        Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "Back")
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(20.dp, 25.dp))
    {
        Spacer(modifier = Modifier.padding(20.dp))

        // Charity
        Text(text = viewModel.charityName,
            color = Color.Black,
            fontSize = Typography.displayMedium.fontSize,
            textAlign = TextAlign.Left
        )

        // Title
        Text(text = viewModel.title,
            color = Color.Black,
            fontWeight = Typography.headlineMedium.fontWeight,
            fontSize = Typography.headlineMedium.fontSize,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.padding(20.dp))

        // Article Text
        Text(text = viewModel.paragraf,
            color = Color.Black,
            fontSize = Typography.displayMedium.fontSize,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
@Preview
fun previewArticle(){
    //ArticleScreen("Putin dræber 2000 børn om dagen", "Dette er ikke så godt. fordi børn normalt ikke burde dø", "Red Barnet")
}