package com.example.kind.view.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun KindButton(Onclick: () -> Unit, TextProvider: String,) {
    Button(
        onClick = { Onclick },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .width(280.dp)
            //.fillMaxWidth()
    ) {
        Text(
            text = TextProvider,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun KindButton(Onclick: () -> Unit, Width: Int, TextProvider: String,) {
    Button(
        onClick = { Onclick },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .width(Width.dp)
    ) {
        Text(
            text = TextProvider,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun KindButtonBackground(Onclick: () -> Unit, TextProvider: String,) {
    Button(
        onClick = { Onclick },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier
            .width(280.dp)
    ) {
        Text(
            text = TextProvider,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
fun KindButtonBackground(Onclick: () -> Unit, Width: Int, TextProvider: String,) {
    Button(
        onClick = { Onclick },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier
            .width(Width.dp)
    ) {
        Text(
            text = TextProvider,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
fun KindButtonOutlined(Onclick: () -> Unit, TextProvider: String,) {
    Button(
        onClick = { Onclick },
        colors = ButtonDefaults.buttonColors(
            //containerColor = MaterialTheme.colorScheme.background,
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .width(280.dp)
    ) {
        Text(
            text = TextProvider,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
fun KindButtonOutlined(Onclick: () -> Unit, Width: Int, TextProvider: String,) {
    Button(
        onClick = { Onclick },
        colors = ButtonDefaults.buttonColors(
            //containerColor = MaterialTheme.colorScheme.background,
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .width(Width.dp)
    ) {
        Text(
            text = TextProvider,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
fun KindButtonDanger(Onclick: () -> Unit, TextProvider: String,){
    Button(
        onClick = { Onclick },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            //contentColor = MaterialTheme.colorScheme.onErrorContainer
        ),
        modifier = Modifier
            .width(280.dp)
    ) {
        Text(
            text = TextProvider,
            color = MaterialTheme.colorScheme.onErrorContainer,
        )
    }
}

@Composable
fun KindButtonDanger(Onclick: () -> Unit, Width: Int, TextProvider: String,){
    Button(
        onClick = { Onclick },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            //contentColor = MaterialTheme.colorScheme.onErrorContainer
        ),
        modifier = Modifier
            .width(Width.dp)
    ) {
        Text(
            text = TextProvider,
            color = MaterialTheme.colorScheme.onErrorContainer,
        )
    }
}