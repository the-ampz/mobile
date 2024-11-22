package dev.ericknathan.ampz.ui.activities.pages

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.R
import dev.ericknathan.ampz.controllers.AuthController
import dev.ericknathan.ampz.models.Gender
import dev.ericknathan.ampz.ui.activities.HomeActivity
import dev.ericknathan.ampz.ui.activities.WelcomeActivity
import dev.ericknathan.ampz.ui.components.FormButton
import dev.ericknathan.ampz.ui.components.FormButtonSecondary
import dev.ericknathan.ampz.ui.components.FormField
import dev.ericknathan.ampz.ui.components.Header
import dev.ericknathan.ampz.ui.theme.AmpzTheme

data class GenderItem(val value : Gender, val label: String)
val genderList = listOf(
    GenderItem(Gender.BOY, "Masculino"),
    GenderItem(Gender.GIRL, "Feminino")
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfilePage() {
    val context = LocalContext.current as ComponentActivity
    val controller = AuthController(context)
    val user = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    var errorsList by remember { mutableStateOf(mutableMapOf<String, String>()) }

    var name by remember { mutableStateOf(user.getString("name", "") ?: "") }
    val email = user.getString("email", "") ?: ""
    var birthDate by remember { mutableStateOf(user.getString("birthDate", "") ?: "") }
    var gender by remember { mutableStateOf(user.getString("gender", "BOY") ?: "BOY") }
    var isSubmitting by remember { mutableStateOf(false) }
    var isSigningOut by remember { mutableStateOf(false) }
    val avatar = when (gender) {
        Gender.GIRL.toString() -> painterResource(id = R.drawable.avatar_girl)
        else -> painterResource(id = R.drawable.avatar_boy)
    }


    AmpzTheme {
        Column(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(text = "Perfil")

            // Imagem de perfil
            Image(
                painter = avatar,
                contentDescription = "Avatar da Criança",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FormField(
                    value = name,
                    onValueChange = {
                        name = it
                        errorsList.remove("name")
                    },
                    label = "Nome completo",
                    placeholder = "Insira seu nome completo",
                    error = errorsList["name"]
                )

                FormField(
                    value = birthDate,
                    onValueChange = {
                        birthDate = it
                        errorsList.remove("birthDate")
                    },
                    label = "Data de nascimento",
                    placeholder = "DD/MM/AAAA",
                    keyboardType = KeyboardType.Number,
                    error = errorsList["birthDate"]
                )

                FormField(
                    value = email,
                    onValueChange = {},
                    label = "Endereço de E-mail",
                    placeholder = "Endereço de E-mail",
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Edição bloqueada"
                        )
                    }
                )

                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    genderList.forEachIndexed { index, item ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(index = index, count = genderList.size),
                            onClick = { gender = item.value.toString() },
                            selected = gender == item.value.toString()
                        ) {
                            Text(item.label)
                        }
                    }
                }

                FormButton(
                    isLoading = isSubmitting,
                    onClick = {
                        isSubmitting = true

                        controller.updateProfile(
                            name,
                            birthDate,
                            gender,
                            onSubmit = { isSubmitting = false },
                            onError = {
                                errorsList = it
                                isSubmitting = false
                            }
                        )
                    },
                    text = "Salvar Alterações"
                )

                FormButtonSecondary(
                    isLoading = isSigningOut,
                    onClick = {
                        isSigningOut = true

                        controller.signOut(
                            user.getInt("id", 0),
                            onSubmit = { isSigningOut = false }
                        )
                    },
                    text = "Deslogar"
                )
            }
        }
    }
}
