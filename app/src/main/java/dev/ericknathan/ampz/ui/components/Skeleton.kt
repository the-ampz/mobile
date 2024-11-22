package dev.ericknathan.ampz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Skeleton(
    modifier : Modifier = Modifier,
    radius: Int = 8
) {
    Box(
        modifier = modifier
            .background(
                color = Color.LightGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(radius)
            )
    )
}