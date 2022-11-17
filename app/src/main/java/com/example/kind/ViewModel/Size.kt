package com.example.kind.ViewModel

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Size(
    val default: Dp = 0.dp,
    //100 1 500 50 40 200 150 270 420 320 12 250 85

)

val LocalSize = compositionLocalOf { Size() }

val MaterialTheme.size : Size
    @Composable
    @ReadOnlyComposable
    get() = LocalSize.current