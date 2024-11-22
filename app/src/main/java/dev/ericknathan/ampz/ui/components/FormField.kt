package dev.ericknathan.ampz.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import dev.ericknathan.ampz.R
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource

@Composable
fun FormField(
    value: String,
    label: String = "",
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false,
    trailingIcon : @Composable (() -> Unit)? = null
) {
    var passwordVisible = rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value,
        onValueChange,
        label = { Text(label, fontSize = 14.sp) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        textStyle = TextStyle(fontSize = 14.sp),
        visualTransformation = if (!passwordVisible.value && keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        readOnly = readOnly,
        trailingIcon = {
            if (keyboardType == KeyboardType.Password) {
                androidx.compose.material3.IconButton(
                    onClick = { passwordVisible.value = !passwordVisible.value }
                ) {
                    Icon(
                        painter = painterResource(id =
                            if (passwordVisible.value) R.drawable.icon_visibility_off
                            else R.drawable.icon_visibility
                        ),
                        contentDescription = "Visualizar senha"
                    )
                }
            } else {
                trailingIcon?.invoke()
            }
        }
    )
}
