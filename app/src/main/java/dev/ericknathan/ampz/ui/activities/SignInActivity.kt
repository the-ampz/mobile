package dev.ericknathan.ampz.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.R
import dev.ericknathan.ampz.ui.components.FormButton
import dev.ericknathan.ampz.ui.components.FormField
import dev.ericknathan.ampz.ui.components.IconButton
import dev.ericknathan.ampz.ui.components.LinkButton
import dev.ericknathan.ampz.ui.components.Logo
import dev.ericknathan.ampz.ui.components.TermsPrivacyText
import dev.ericknathan.ampz.ui.theme.AmpzTheme
import dev.ericknathan.ampz.ui.theme.Typography

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmpzTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        SignInScreen()
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun SignInScreen() {
        val context = LocalContext.current as SignInActivity
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

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
                        text = "Faça login na sua conta",
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
                FormField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = "Senha",
                    placeholder = "Insira sua senha",
                    keyboardType = KeyboardType.Password
                )
                Box(
                    modifier = Modifier.fillMaxWidth().padding(0.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    LinkButton(
                        text = "Esqueci minha senha",
                        onClick = {
                            context.startActivity(Intent(context, RecoverPasswordActivity::class.java))
                        },
                    )
                }
                FormButton(
                    text = "Entrar",
                    onClick = {
                        context.startActivity(Intent(context, HomeActivity::class.java))
                    }
                )
                TermsPrivacyText()
            }
        }
    }
}
