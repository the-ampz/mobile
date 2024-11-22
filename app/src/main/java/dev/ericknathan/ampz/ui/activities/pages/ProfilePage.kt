package dev.ericknathan.ampz.ui.activities.pages

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ericknathan.ampz.R
import dev.ericknathan.ampz.ui.components.FormButton
import dev.ericknathan.ampz.ui.components.FormButtonSecondary
import dev.ericknathan.ampz.ui.components.FormField
import dev.ericknathan.ampz.ui.components.Header
import dev.ericknathan.ampz.ui.theme.AmpzTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfilePage() {
    var name by remember { mutableStateOf("Maria Nogueira") }
    val emailResponsavel = "responsavel@email.com"
    var birthDate by remember { mutableStateOf("01/01/2015") }
    var gender by remember { mutableStateOf("Feminino") }

    val genderList = listOf("Masculino", "Feminino")
    val avatar = when (gender) {
        "Feminino" -> painterResource(id = R.drawable.avatar_girl)
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
                    onValueChange = { name = it },
                    label = "Nome completo",
                    placeholder = "Insira seu nome completo"
                )

                FormField(
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    label = "Data de nascimento",
                    placeholder = "DD/MM/AAAA",
                    keyboardType = KeyboardType.Number
                )

                FormField(
                    value = emailResponsavel,
                    onValueChange = {},
                    label = "Email do Responsável",
                    placeholder = "Email do Responsável",
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
                    genderList.forEachIndexed { index, label ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(index = index, count = genderList.size),
                            onClick = { gender = label },
                            selected = label == gender
                        ) {
                            Text(label)
                        }
                    }
                }

                FormButton(
                    onClick = {},
                    text = "Salvar Alterações"
                )

                FormButtonSecondary(
                    onClick = {},
                    text = "Deslogar"
                )
            }
        }
    }
}
