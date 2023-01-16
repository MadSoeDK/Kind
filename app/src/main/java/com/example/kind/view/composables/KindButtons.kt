package com.example.kind.view.composables

import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
            fontWeight = FontWeight.Medium,
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
            fontWeight = FontWeight.Medium,
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
            fontWeight = FontWeight.Medium,
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
            fontWeight = FontWeight.Medium,
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
                containerColor = MaterialTheme.colorScheme.primary
            )
    ) {
        Text(
            text = textProvider,
            //color = MaterialTheme.colorScheme.onBackground,
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun KindButtonOutlined(onClick: () -> Unit, Width: Int, TextProvider: String,) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            //containerColor = MaterialTheme.colorScheme.background,
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .width(Width.dp)
    ) {
        Text(
            text = TextProvider,
            //color = MaterialTheme.colorScheme.onBackground,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun KindButtonDanger(onClick: () -> Unit, TextProvider: String,){
    Button(
        onClick = { onClick },
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
            fontWeight = FontWeight.Medium,
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
            fontWeight = FontWeight.Medium,
        )
    }
}