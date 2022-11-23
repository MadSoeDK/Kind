package com.example.kind.view.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Size(
    val default: Dp = 0.dp,
    val xxxxxxs: Dp = 1.dp,
    val xxxxxs: Dp = 12.dp,
    val xxxxs: Dp = 30.dp,
    val xxxs: Dp = 40.dp,
    val xxs: Dp = 50.dp,
    val xs: Dp = 85.dp,
    val s: Dp = 100.dp,
    val m: Dp = 140.dp,
    val l: Dp = 150.dp,
    val xl: Dp = 200.dp,
    val xxl: Dp = 250.dp,
    val xxxl: Dp = 270.dp,
    val xxxxl: Dp = 320.dp,
    val xxxxxl: Dp = 420.dp,
    val xxxxxxl: Dp = 500.dp,

    //TODO 140 - kindCard - m
    //TODO 30 - KindApp - 4xs

)

val LocalSize = compositionLocalOf { Size() }

val size : Size
    @Composable
    @ReadOnlyComposable
    get() = LocalSize.current