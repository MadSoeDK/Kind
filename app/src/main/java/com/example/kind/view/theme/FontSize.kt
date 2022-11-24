package com.example.kind.view.theme

import androidx.compose.material3.MaterialTheme
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

    val header: TextUnit = 16.sp,
    val subheader: TextUnit = 14.sp,
    val body: TextUnit = 12.sp,
    val title: TextUnit = 24.sp,
    val buttonText: TextUnit = 14.sp,
    val announcement: TextUnit = 40.sp,
    val signUpHeader: TextUnit = 20.sp,

    //listHeader: 15 listContent: 13
)

val LocalFontSize = compositionLocalOf { FontSize() }

val fontSize : FontSize
    @Composable
    @ReadOnlyComposable
    get() = LocalFontSize.current