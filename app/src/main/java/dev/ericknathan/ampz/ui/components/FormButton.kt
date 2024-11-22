package dev.ericknathan.ampz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ericknathan.ampz.ui.theme.Secondary500
import dev.ericknathan.ampz.ui.theme.Typography

@Composable
fun FormButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    Button(
        onClick,
        modifier = modifier.fillMaxWidth()
            .height(48.dp)
            .alpha(if (isLoading) 0.9f else 1f)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        enabled = !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun FormButtonSecondary(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    OutlinedButton(
        onClick,
        modifier = modifier.fillMaxWidth()
            .height(48.dp)
            .alpha(if (isLoading) 0.9f else 1f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Secondary500),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = MaterialTheme.colorScheme.onSecondary,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun IconButton(
    icon: Int,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick,
        modifier = Modifier.size(48.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
        )
    }
}

@Composable
fun LinkButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick,
        modifier = Modifier.padding(0.dp),
        contentPadding = PaddingValues(4.dp),
        shape = RoundedCornerShape(4.dp),

    ) {
        Text(
            text,
            textDecoration = TextDecoration.Underline,
            style = Typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start,
        )
    }
}