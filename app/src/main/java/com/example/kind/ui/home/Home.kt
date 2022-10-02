package com.example.kind.ui.home

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    // Logic etc...
    fun getText(): String {
        return "Home screen"
    }
}

@Composable
fun HomeScreen(
    textProvider: () -> String,
    navigateToPage: () -> Unit
) {
    Button(onClick = { navigateToPage() }) {
        Text(text = textProvider())
    }
}

/*@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen { "Home screen preview" }
}*/