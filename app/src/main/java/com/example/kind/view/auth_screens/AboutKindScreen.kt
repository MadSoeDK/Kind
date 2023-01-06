package com.example.kind.view.signup_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kind.R

@Composable
fun AboutKindScreen(
    next: () -> Unit
){
    Column {
        val textLogo = painterResource(id = R.drawable.textlogo)
        Image(
            painter = textLogo, contentDescription = null, modifier = Modifier
                .height(200.dp)
                .width(200.dp)
        )
        val painter = painterResource(id = R.drawable.bekindsplashart2)
        Image(
            painter = painter, contentDescription = null, modifier = Modifier
                .height(200.dp)
                .width(200.dp)
        )
        Text(text = "Some descriptive about text")
        Button(onClick = { next() }) {
            Text(text = "Next")
        }
    }
}