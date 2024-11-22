package dev.ericknathan.ampz.ui.activities.pages

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import dev.ericknathan.ampz.ui.components.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.controllers.ConsumptionController
import dev.ericknathan.ampz.models.Challenge
import dev.ericknathan.ampz.ui.components.FormButton
import dev.ericknathan.ampz.ui.components.Header
import dev.ericknathan.ampz.ui.components.Skeleton
import dev.ericknathan.ampz.ui.theme.AmpzTheme

@Preview(showBackground = true)
@Composable
fun ChallengesPage() {
    val context = LocalContext.current as ComponentActivity
    val controller = remember { ConsumptionController(context) }
    val user = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    val userId = user.getInt("id", 0)

    var challenges = remember { mutableStateListOf<Challenge>() }
    LaunchedEffect(userId) {
        controller.getChallenges(
            id = userId,
            onSubmit = { challengesList ->
                challenges.addAll(challengesList)
            }
        )
    }

    AmpzTheme {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
        ) {
            Header(
                text = "Desafios",
                modifier = Modifier.padding(bottom = 16.dp, top = 24.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if(challenges.isNotEmpty()) {
                    challenges.forEach { challenge ->
                        ChallengeCard(challenge)
                    }
                } else {
                    (0..2).forEach { _ ->
                        Skeleton(
                            modifier = Modifier.height(225.dp).fillMaxWidth(),
                            radius = 8
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChallengeCard(challenge: Challenge) {
    Card(
        title = challenge.title
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = challenge.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                LinearProgressIndicator(
                    progress = challenge.progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )

                Text(
                    text = "${(challenge.progress * 100).toInt()}% concluído",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Recompensa: ${challenge.reward}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (challenge.completed) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Concluído",
                        tint = MaterialTheme.colorScheme.primary
                    )
                } else {
                    FormButton(
                        onClick = { /* Implementar ação para participar/concluir desafio */ },
                        text = "Participar",
                        modifier = Modifier.width(120.dp)
                    )
                }
            }
        }
    }
}
