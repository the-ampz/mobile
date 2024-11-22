package dev.ericknathan.ampz.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.ericknathan.ampz.R
import dev.ericknathan.ampz.ui.components.FormButton
import dev.ericknathan.ampz.ui.components.Logo
import dev.ericknathan.ampz.ui.theme.AmpzTheme
import dev.ericknathan.ampz.ui.theme.Typography

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        val userPreferences = getSharedPreferences("user", MODE_PRIVATE)
        if (userPreferences.contains("name")) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        setContent {
            AmpzTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        WelcomeScreen()
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun WelcomeScreen() {
        val context = LocalContext.current

        AmpzTheme {
            Column(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo()
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 24.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.illustration_welcome),
                        contentDescription = "Imagem de boas-vindas com dois personagens felizes adornados com confetes",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Estamos felizes em te ver por aqui!",
                            style = Typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 32.dp),
                            lineHeight = 36.sp
                        )
                        Text(
                            text = "A forma gamificada para ajudar o mundo economizando energia elétrica e ainda ganhar prêmios! 🚀😎",
                            style = Typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .alpha(0.8f),
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                ) {
                    FormButton(
                        text = "Acessar minha conta",
                        onClick = {
                            context.startActivity(Intent(context, SignInActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}
