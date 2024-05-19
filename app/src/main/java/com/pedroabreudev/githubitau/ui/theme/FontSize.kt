package com.pedroabreudev.githubitau.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Immutable
class FontSize(
    val fontSize_xxs: TextUnit,
    val fontSize_xs: TextUnit,
    val fontSize_sm: TextUnit,
    val fontSize_md: TextUnit,
    val fontSize_xl: TextUnit,
    val fontSize_xxl: TextUnit,
    val fontSize_xxxl: TextUnit,

    ) {

    companion object {
        val default = FontSize(
            fontSize_xxs = 12.sp,
            fontSize_xs = 14.sp,
            fontSize_sm = 16.sp,
            fontSize_md = 18.sp,
            fontSize_xl = 24.sp,
            fontSize_xxl = 28.sp,
            fontSize_xxxl = 32.sp,
        )
    }
}