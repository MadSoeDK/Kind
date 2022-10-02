package com.example.kind

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
}

class KindAppState(
    private val context: Context
) {
}

@Composable
fun rememberKindAppState (
    context: Context = LocalContext.current
) = remember (context) {
    KindAppState(context)
}