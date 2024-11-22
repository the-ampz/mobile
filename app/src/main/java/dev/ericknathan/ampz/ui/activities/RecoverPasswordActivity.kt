package dev.ericknathan.ampz.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.R
import dev.ericknathan.ampz.ui.components.FormButton
import dev.ericknathan.ampz.ui.components.FormButtonSecondary
import dev.ericknathan.ampz.ui.components.FormField
import dev.ericknathan.ampz.ui.components.IconButton
import dev.ericknathan.ampz.ui.components.LinkButton
import dev.ericknathan.ampz.ui.components.Logo
import dev.ericknathan.ampz.ui.components.TermsPrivacyText
import dev.ericknathan.ampz.ui.theme.AmpzTheme
import dev.ericknathan.ampz.ui.theme.Typography

class RecoverPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmpzTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        RecoverPasswordScreen()
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun RecoverPasswordScreen() {
        val context = LocalContext.current as RecoverPasswordActivity
        val email = remember { mutableStateOf("") }

        AmpzTheme {
            Column(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo()
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        icon = R.drawable.icon_chevron_left,
                        onClick = {
                            context.finish()
                        }
                    )
                    Text(
                        text = "Recuperar senha",
                        style = Typography.titleMedium
                    )
                }
                FormField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = "Endereço de E-mail",
                    placeholder = "Insira seu endereço de e-mail",
                    keyboardType = KeyboardType.Email
                )
                FormButton(
                    text = "Enviar e-mail para responsável",
                    onClick = { /* Do something! */ }
                )
                TermsPrivacyText()
            }
        }
    }
}
