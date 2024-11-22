package dev.ericknathan.ampz.ui.activities.pages

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.ui.components.FormButton
import dev.ericknathan.ampz.ui.components.Header
import dev.ericknathan.ampz.ui.theme.AmpzTheme

@Preview(showBackground = true)
@Composable
fun ChallengesPage() {
    val challenges = listOf(
        Challenge(
            title = "Reduza o consumo em 10%",
            description = "Reduza seu consumo diário de energia em pelo menos 10% comparado ao último mês.",
            progress = 0.4f,
            reward = "50 pontos",
            completed = false
        ),
        Challenge(
            title = "Desconecte dispositivos em standby",
            description = "Desconecte dispositivos que consomem energia em modo standby por 5 dias consecutivos.",
            progress = 0.8f,
            reward = "30 pontos",
            completed = false
        ),
        Challenge(
            title = "Complete seu perfil",
            description = "Preencha todos os campos do seu perfil para começar sua jornada de economia de energia.",
            progress = 1f,
            reward = "10 pontos",
            completed = true
        )
    )

    AmpzTheme {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Header(
                text = "Desafios",
                modifier = Modifier.padding(bottom = 16.dp, top = 24.dp)
            )

            challenges.forEach { challenge ->
                ChallengeCard(challenge)
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

            // Barra de progresso
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

            // Recompensa e botão de ação
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

// Modelo de dados para um desafio
data class Challenge(
    val title: String,
    val description: String,
    val progress: Float,
    val reward: String,
    val completed: Boolean
)