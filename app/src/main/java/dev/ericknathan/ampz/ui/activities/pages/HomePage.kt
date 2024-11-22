package dev.ericknathan.ampz.ui.activities.pages

import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.R
import dev.ericknathan.ampz.controllers.ConsumptionController
import dev.ericknathan.ampz.models.Gender
import dev.ericknathan.ampz.ui.components.Card
import dev.ericknathan.ampz.ui.components.Header
import dev.ericknathan.ampz.ui.components.HeaderColor
import dev.ericknathan.ampz.ui.theme.AmpzTheme
import dev.ericknathan.ampz.ui.theme.Primary500
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.PopupProperties
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Preview(showBackground = true)
@Composable
fun HomePage() {
    val context = LocalContext.current as ComponentActivity
    val controller = remember { ConsumptionController(context) }
    val user = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    val userId = user.getInt("id", 0)
    val userGender = if(user.getString("gender", "BOY") == "BOY") Gender.BOY else Gender.GIRL

    var lastDaysConsumption = remember { mutableStateListOf<Double>() }
    LaunchedEffect(userId) {
        controller.getSevenDaysConsumption(
            id = userId,
            onSubmit = { consumption ->
                lastDaysConsumption.addAll(consumption)
            }
        )
    }

    AmpzTheme {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).background(MaterialTheme.colorScheme.background)
        ) {
            Header(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 48.dp),
                text = "Olá, ${user.getString("name", "")}! 👋",
                color = HeaderColor.PRIMARY
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-24).dp)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                LastDayConsumption(lastDaysConsumption)
                SustainabilityScore(user.getInt("score", 0), userGender)
                EcoTips(userGender)
            }
        }
    }
}

@Composable
fun LastDayConsumption(lastDaysConsumption: List<Double>) {
    Card(title = "⚡ Consumo de energia dos últimos 7 dias") {
        val lastSevenDays = (0..<7).map { LocalDate.now().minusDays(it.toLong()) }.reversed()

        if(lastDaysConsumption.isNotEmpty()) {
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 8.dp),
                data = remember {
                    listOf(
                        Line(
                            label = "Consumo de energia (kWh)",
                            values = lastDaysConsumption,
                            color = SolidColor(Primary500),
                            firstGradientFillColor = Primary500.copy(alpha = .5f),
                            secondGradientFillColor = Color.Transparent,
                            strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                            gradientAnimationDelay = 1000,
                            drawStyle = DrawStyle.Stroke(width = 2.dp),
                        )
                    )
                },
                labelHelperProperties = LabelHelperProperties(enabled = false),
                gridProperties = GridProperties(enabled = false),
                indicatorProperties = HorizontalIndicatorProperties(enabled = false),
                animationMode = AnimationMode.Together(delayBuilder = { it * 500L }),
                popupProperties = PopupProperties(
                    contentBuilder = { value -> "${value.toInt()} kWh" },
                    containerColor = MaterialTheme.colorScheme.onBackground,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.background),

                    )
            )
        } else {
            Column(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Carregando...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            lastSevenDays.forEachIndexed { index, date ->
                Text(
                    text = DateTimeFormatter.ofPattern("dd/MM").format(date),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Composable
fun SustainabilityScore(currentPoints: Int = 0, gender: Gender) {
    val objectivePoints = 1000

    Card(title = "💎 Pontuação de sustentabilidade") {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = if(gender == Gender.BOY) R.drawable.illustration_boy_happy else R.drawable.illustration_girl_happy),
                contentDescription = "Feliz",
                modifier = Modifier.height(96.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "$currentPoints pontos",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "Você está indo muito bem!",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 12.dp),
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Meta do desafio",
                            style = MaterialTheme.typography.bodySmall,
                        )
                        Text(
                            text = "$objectivePoints pontos",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    LinearProgressIndicator(
                        progress = { currentPoints.toFloat() / objectivePoints.toFloat() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun EcoTips(gender: Gender) {
    val dicas = listOf(
        "Apague as luzes quando o quarto estiver vazio.",
        "Use luzes suaves à noite para economia e melhor sono.",
        "Desconecte dispositivos não utilizados para evitar consumo em standby.",
        "Monitore o consumo diário e ajuste os hábitos.",
    )
    var currentIndex by remember { mutableIntStateOf(0) }

    Card(title = "🧠 Dicas para economizar energia") {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(modifier = Modifier.weight(1f)) {
                LaunchedEffect(Unit) {
                    while (true) {
                        delay(5000)
                        currentIndex = (currentIndex + 1) % dicas.size
                    }
                }

                AnimatedContent(
                    targetState = currentIndex, label = "Dica",
                    transitionSpec = { fadeIn(animationSpec = tween(500)) togetherWith fadeOut(animationSpec = tween(500)) }
                ) { targetIndex ->
                    Text(
                        text = dicas[targetIndex],
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            Image(
                painter = painterResource(id = if(gender == Gender.BOY) R.drawable.illustration_boy_tip else R.drawable.illustration_girl_tip),
                contentDescription = "Dica",
                modifier = Modifier.height(96.dp)
            )
        }
    }
}
