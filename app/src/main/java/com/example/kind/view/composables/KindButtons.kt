package com.example.kind.view.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KindButton(onClick: () -> Unit, textProvider: String) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .width(280.dp)
    ) {
        Text(
            text = textProvider,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun KindButtonEdit(Onclick: () -> Unit, width: Int, TextProvider: String,) {
    Button(
        onClick = { Onclick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .width(width.dp)
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
fun KindButtonOutlined(onClick: () -> Unit, textProvider: String) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .width(280.dp),
        colors = ButtonDefaults
            .outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary,
        )
    ) {
        Text(text = textProvider)
    }
}

@Composable
fun KindButtonOutlined(onClick: () -> Unit, width: Int, textProvider: String) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.width(width.dp),
        colors = ButtonDefaults
            .outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary,
        )
    ) {
        Text(text = textProvider)
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