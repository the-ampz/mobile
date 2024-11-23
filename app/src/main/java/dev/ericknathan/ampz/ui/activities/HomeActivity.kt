package dev.ericknathan.ampz.ui.activities

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import dev.ericknathan.ampz.controllers.AuthController
import dev.ericknathan.ampz.controllers.ConsumptionController
import dev.ericknathan.ampz.models.Challenge
import dev.ericknathan.ampz.models.NavItem
import dev.ericknathan.ampz.ui.activities.pages.ChallengesPage
import dev.ericknathan.ampz.ui.activities.pages.DevicesPage
import dev.ericknathan.ampz.ui.activities.pages.HomePage
import dev.ericknathan.ampz.ui.activities.pages.RankingPage
import dev.ericknathan.ampz.ui.activities.pages.ProfilePage
import dev.ericknathan.ampz.ui.theme.AmpzTheme
import dev.ericknathan.ampz.ui.theme.Primary500

class HomeActivity : ComponentActivity() {
    val navItemList = listOf(
        NavItem("Início", Icons.Default.Home),
        NavItem("Dispositivos", Icons.Default.Devices),
        NavItem("Ranking", Icons.Default.Leaderboard, "2°"),
        NavItem("Desafios", Icons.Default.Star, "3"),
        NavItem("Perfil", Icons.Default.Person),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun HomeScreen() {
        var selectedIndex by remember { mutableIntStateOf(0) }

        enableEdgeToEdge(
            statusBarStyle = if(selectedIndex == 0) SystemBarStyle.dark(Primary500.toArgb()) else SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
        )
        AmpzTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    NavigationBar {
                        navItemList.forEachIndexed() { index, item ->
                            NavigationBarItem(
                                selected = index == selectedIndex,
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if(item.badgeCount != null) {
                                                Badge(
                                                    containerColor = MaterialTheme.colorScheme.primary,
                                                ) {
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            }
                                        },
                                    ) {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.label,
                                        )
                                    }
                                },
                                label = { Text(item.label) },
                                onClick = {
                                    selectedIndex = index
                                },
                            )
                        }
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding).background(
                        if(selectedIndex == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
                    )
                ) {
                    when (selectedIndex) {
                        0 -> HomePage()
                        1 -> DevicesPage()
                        2 -> RankingPage()
                        3 -> ChallengesPage()
                        4 -> ProfilePage()
                    }
                }
            }
        }
    }
}
