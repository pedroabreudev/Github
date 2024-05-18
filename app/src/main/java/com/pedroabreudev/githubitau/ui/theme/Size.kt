package com.pedroabreudev.githubitau.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class Size(
    val size_6: Dp,
    val size_10: Dp,
    val size_16: Dp,
) {
    companion object {
        val default = Size(
            size_6 = 6.dp,
            size_10 = 10.dp,
            size_16 = 16.dp
        )
    }
}