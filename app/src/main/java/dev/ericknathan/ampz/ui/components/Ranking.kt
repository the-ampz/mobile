package dev.ericknathan.ampz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.alpha
import dev.ericknathan.ampz.R
import dev.ericknathan.ampz.models.User
import dev.ericknathan.ampz.models.getUserAvatar
import dev.ericknathan.ampz.ui.theme.RankBronze
import dev.ericknathan.ampz.ui.theme.RankGold
import dev.ericknathan.ampz.ui.theme.RankSilver

@Composable
fun RankingHighlight(place : Int, user : User) {
    val color = when (place) {
        1 -> RankGold
        2 -> RankSilver
        3 -> RankBronze
        else -> MaterialTheme.colorScheme.primary
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$place°",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = color
        )

        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = getUserAvatar(user.gender)),
                contentDescription = "Avatar",
                modifier = Modifier.size((110 - (place * 10)).dp).clip(CircleShape).border(4.dp, color, CircleShape).padding(vertical = 8 .dp),
                alignment = Alignment.Center
            )
        }

        Text(
            text = user.name,
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            text = user.score.toString(),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun RankingItem(place : Int, user : User) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$place°",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp).background(color = MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(32.dp)),
        ) {
            Image(
                painter = painterResource(id = getUserAvatar(user.gender)),
                contentDescription = "Avatar",
                modifier = Modifier.size(64.dp).clip(CircleShape),
                alignment = Alignment.Center
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = user.score.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

}