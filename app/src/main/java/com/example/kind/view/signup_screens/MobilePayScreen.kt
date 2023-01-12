package com.example.kind.view.signup_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.R

@Composable
fun MobilePayScreen(){
    //TODO Implement, currently just a temp image
    val painter = painterResource(id = R.drawable.mobilepayscreen)
    Image(
        painter = painter, contentDescription = null, modifier = Modifier
            .height(1000.dp)
            .width(1000.dp)
    )
}

@Preview ()
@Composable
fun MobilepayScreenPreview(){
    MobilePayScreen()
}