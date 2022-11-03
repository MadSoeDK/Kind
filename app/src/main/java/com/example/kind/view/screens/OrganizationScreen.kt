package com.example.kind.view.screens

import android.provider.ContactsContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.view.composables.CardListHorizontalScroll
import com.example.kind.view.theme.Typography
import com.example.kind.view.theme.subHeading
import com.example.kind.ViewModel.HomeViewModel

@Composable
fun OrganizationScreen()
{
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(color = Color.White)) {

        Row(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth())
        {
            Box(modifier = Modifier
                .background(color = Color.LightGray)
                .fillMaxWidth()
                .fillMaxHeight()
                )
            {

            }
        }

        Row(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .align(alignment = Alignment.CenterHorizontally)
        {
            Box(modifier = Modifier
                .clip(CircleShape)
                .background(color = Color.LightGray)
                .width(200.dp)
                .height(200.dp)
            )
            {

            }
        }
    }
}

@Composable
@Preview
fun OrganizationScreenPreview()
{
    OrganizationScreen()
}