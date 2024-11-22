package dev.ericknathan.ampz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.ui.theme.Typography

enum class HeaderColor {
    PRIMARY,
    DEFAULT
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    text : String,
    color : HeaderColor = HeaderColor.DEFAULT
) {
    Column(
        modifier = Modifier.fillMaxWidth().background(
            when (color) {
                HeaderColor.PRIMARY -> MaterialTheme.colorScheme.primary
                HeaderColor.DEFAULT -> Color.Transparent
            }
        ),
    ) {
        Row(
            modifier,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Logo(size = 32, color = when (color) {
                HeaderColor.PRIMARY -> Color.White
                HeaderColor.DEFAULT -> MaterialTheme.colorScheme.primary
            })
            Text(
                text,
                style = Typography.titleMedium,
                color = when (color) {
                    HeaderColor.PRIMARY -> MaterialTheme.colorScheme.onPrimary
                    HeaderColor.DEFAULT -> MaterialTheme.colorScheme.onSurface
                }
            )
        }
    }
}