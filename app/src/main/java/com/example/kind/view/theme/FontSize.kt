package com.example.kind.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class FontSize(
    val default: TextUnit = 0.sp,
    val s: TextUnit = 12.sp,
    val m: TextUnit = 14.sp,
    val l: TextUnit = 18.sp,
    val xl: TextUnit = 24.sp,
)

val LocalFontSize = compositionLocalOf { FontSize() }

val MaterialTheme.fontSize : FontSize
    @Composable
    @ReadOnlyComposable
    get() = LocalFontSize.current