package dev.ericknathan.ampz.ui.activities.pages

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.controllers.ConsumptionController
import dev.ericknathan.ampz.models.Challenge
import dev.ericknathan.ampz.models.Gender
import dev.ericknathan.ampz.models.RankingUser
import dev.ericknathan.ampz.models.User
import dev.ericknathan.ampz.ui.components.Header
import dev.ericknathan.ampz.ui.components.RankingHighlight
import dev.ericknathan.ampz.ui.components.RankingItem
import dev.ericknathan.ampz.ui.components.Skeleton
import dev.ericknathan.ampz.ui.theme.AmpzTheme

@Preview(showBackground = true)
@Composable
fun RankingPage() {
    val context = LocalContext.current as ComponentActivity
    val controller = remember { ConsumptionController(context) }
    val user = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    val userId = user.getInt("id", 0)

    var ranking = remember { mutableStateListOf<RankingUser>() }
    LaunchedEffect(userId) {
        controller.getCommunityRanking(
            id = userId,
            onSubmit = { rankingUsers ->
                ranking.addAll(rankingUsers)
            }
        )
    }

    AmpzTheme {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(text = "Ranking - FIAP School 3°B", modifier = Modifier.padding(top = 24.dp))

            if(ranking.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    RankingHighlight(ranking.find { it.position == 2 }!!)
                    RankingHighlight(ranking.find { it.position == 1 }!!)
                    RankingHighlight(ranking.find { it.position == 3 }!!)
                }

                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    ranking.forEachIndexed { index, rankingUser ->
                        if(index > 2) {
                            RankingItem(rankingUser)
                        }
                    }
                }
            } else {
                Skeleton(
                    modifier = Modifier.height(173.dp).fillMaxWidth(),
                    radius = 8
                )

                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    (0..10).forEachIndexed { index, _ ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "${index + 4}°",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                            )

                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                            ) {
                                Skeleton(
                                    modifier = Modifier.height(64.dp).fillMaxWidth(),
                                    radius = 64
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
