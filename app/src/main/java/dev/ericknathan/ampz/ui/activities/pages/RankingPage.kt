package dev.ericknathan.ampz.ui.activities.pages

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.models.Gender
import dev.ericknathan.ampz.models.User
import dev.ericknathan.ampz.ui.components.Header
import dev.ericknathan.ampz.ui.components.RankingHighlight
import dev.ericknathan.ampz.ui.components.RankingItem
import dev.ericknathan.ampz.ui.theme.AmpzTheme

@Preview(showBackground = true)
@Composable
fun RankingPage() {
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

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                RankingHighlight(2, User("Maria Nogueira", 900, Gender.GIRL))
                RankingHighlight(1, User("João Silva", 1000, Gender.BOY))
                RankingHighlight(3, User("Pedro Henrique", 800, Gender.BOY))
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                RankingItem(4, User("Ana Beatriz", 700, Gender.GIRL))
                RankingItem(5, User("Lucas Santos", 600, Gender.BOY))
                RankingItem(6, User("Juliana Oliveira", 500, Gender.GIRL))
                RankingItem(7, User("Rafaela Souza", 400, Gender.GIRL))
                RankingItem(8, User("Fernando Pereira", 300, Gender.BOY))
                RankingItem(9, User("Mariana Silva", 250, Gender.GIRL))
                RankingItem(10, User("João Pedro", 200, Gender.BOY))
                RankingItem(11, User("Camila Rocha", 150, Gender.GIRL))
                RankingItem(12, User("Gustavo Lima", 100, Gender.BOY))
                RankingItem(13, User("Bianca Costa", 90, Gender.GIRL))
                RankingItem(14, User("Thiago Almeida", 80, Gender.BOY))
                RankingItem(15, User("Carolina Mendes", 70, Gender.GIRL))
                RankingItem(16, User("Felipe Araújo", 60, Gender.BOY))
            }
        }
    }
}
