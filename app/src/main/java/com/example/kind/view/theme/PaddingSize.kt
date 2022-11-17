package com.example.kind.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class PaddingSize(
    val default: Dp = 0.dp,
    val xs: Dp = 5.dp,
    val s: Dp = 8.dp,
    val m: Dp = 10.dp,
    val l: Dp = 25.dp,
    val xl: Dp = 50.dp,

    //sizes to add:
    // 6 - PortfolioScreen
    // 16 - PortFolioTable
    // 20 - KindCharityCard, PortfolioScreen
    // 30 - PortfolioScreen
    // 40 - PortfolioScreen

    //5 - xxxs
    //6 - xxs
    //8 - xs
    //10 - s
    //16 - m
    //20 - l
    //25 - xl
    //30 - xxl
    //40 - xxxl
    //50 - xxxxl
)

val LocalPaddingSize = compositionLocalOf { PaddingSize() }

val MaterialTheme.paddingSize : PaddingSize
    @Composable
    @ReadOnlyComposable
    get() = LocalPaddingSize.current