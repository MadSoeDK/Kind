package com.example.kind.view.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DivDpSize(
    val default: Dp = 0.dp,
    val xs: Dp = 50.dp,
    val s: Dp = 65.dp,
    val m: Dp = 70.dp,
    val l: Dp = 90.dp,
    val xl: Dp = 120.dp,

    val stdSpacer: Dp = 20.dp,

)

val LocalDivDPSize = compositionLocalOf { DivDpSize() }

val divDpSize : DivDpSize
    @Composable
    @ReadOnlyComposable
    get() = LocalDivDPSize.current