package com.example.kind.view.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class PaddingSize(
    val default: Dp = 0.dp,
    val xxxs: Dp = 5.dp,
    val xxs: Dp = 6.dp,
    val xs: Dp = 8.dp,
    val s: Dp = 10.dp,
    val m: Dp = 16.dp,
    val l: Dp = 20.dp,
    val xl: Dp = 25.dp,
    val xxl: Dp = 30.dp,
    val xxxl: Dp = 40.dp,
    val xxxxl: Dp = 50.dp,

    val standard: Dp = 10.dp,

)

val LocalPaddingSize = compositionLocalOf { PaddingSize() }

val paddingSize : PaddingSize
    @Composable
    @ReadOnlyComposable
    get() = LocalPaddingSize.current