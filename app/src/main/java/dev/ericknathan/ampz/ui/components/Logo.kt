package dev.ericknathan.ampz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.R

@Composable
fun Logo(size : Int = 40, color : Color = MaterialTheme.colorScheme.primary) {
    Icon(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo da Ampz",
        modifier = Modifier.size(size.dp),
        tint = color
    )
}