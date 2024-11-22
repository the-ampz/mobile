package dev.ericknathan.ampz.controllers

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import dev.ericknathan.ampz.models.Profile
import dev.ericknathan.ampz.repositories.AuthRepository
import dev.ericknathan.ampz.ui.activities.HomeActivity
import dev.ericknathan.ampz.ui.activities.WelcomeActivity
import dev.ericknathan.ampz.utils.Validators

class AuthController(private val context: ComponentActivity) {
    private val repository = AuthRepository()

    fun signIn(email: String, password: String, onSubmit: () -> Unit, onError: (MutableMap<String, String>) -> Unit) {
        var errorsList: MutableMap<String, String> = mutableMapOf()

        if(email.isEmpty()) { errorsList["email"] = "E-mail é obrigatório" }
        else if(!Validators.isEmailValid(email)) { errorsList["email"] = "E-mail inválido" }

        if(password.isEmpty()) { errorsList["password"] = "Senha é obrigatória" }
        else if(!Validators.isPasswordValid(password)) { errorsList["password"] = "Senha deve ter no mínimo 8 caracteres" }

        if(errorsList.isNotEmpty()) {
            return onError(errorsList)
        }

        repository.signIn(
            email,
            password,
            onSuccess = { user ->
                context.runOnUiThread {
                    val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putInt("id", user.id)
                    editor.putString("name", user.name)
                    editor.putInt("score", user.score)
                    editor.putString("gender", user.gender)
                    editor.putString("email", email)
                    editor.putString("birthDate", user.birthDate)
                    editor.apply()

                    Toast.makeText(context, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context, HomeActivity::class.java))

                    onSubmit()
                }
            },
            onFailure = { error ->
                context.runOnUiThread {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    onSubmit()
                }
            }
        )
    }

    fun recoverPassword(email: String, onSubmit: () -> Unit, onError: (MutableMap<String, String>) -> Unit) {
        var errorsList: MutableMap<String, String> = mutableMapOf()

        if(email.isEmpty()) { errorsList["email"] = "E-mail é obrigatório" }
        else if(!Validators.isEmailValid(email)) { errorsList["email"] = "E-mail inválido" }

        if(errorsList.isNotEmpty()) {
            return onError(errorsList)
        }

        repository.recoverPassword(
            email,
            onSuccess = {
                context.runOnUiThread {
                    Toast.makeText(context, "E-mail de recuperação enviado para seu responsável", Toast.LENGTH_SHORT).show()
                    context.finish()

                    onSubmit()
                }
            },
            onFailure = { error ->
                context.runOnUiThread {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()

                    onSubmit()
                }
            }
        )
    }

    fun signOut(id : Int, onSubmit: () -> Unit) {
        repository.signOut(
            id,
            onSuccess = {
                context.runOnUiThread {
                    context.getSharedPreferences("user", Context.MODE_PRIVATE).edit().clear().apply()
                    context.startActivity(Intent(context, WelcomeActivity::class.java))
                    context.finish()

                    onSubmit()
                }
            },
            onFailure = { error ->
                context.runOnUiThread {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()

                    onSubmit()
                }
            }
        )
    }

    fun updateProfile(name: String, birthDate: String, gender: String, onSubmit: () -> Unit, onError: (MutableMap<String, String>) -> Unit) {
        var errorsList: MutableMap<String, String> = mutableMapOf()

        if(name.isEmpty()) { errorsList["name"] = "Nome é obrigatório" }
        else if(!Validators.isNameValid(name)) { errorsList["name"] = "Nome inválido" }

        if(birthDate.isEmpty()) { errorsList["birthDate"] = "Data de nascimento é obrigatória" }
        else if(!Validators.isBirthDateValid(birthDate)) { errorsList["birthDate"] = "Data de nascimento inválida" }

        if(errorsList.isNotEmpty()) {
            return onError(errorsList)
        }

        val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        val newProfile = Profile(
            id = sharedPreferences.getInt("id", 0),
            name,
            score = sharedPreferences.getInt("score", 0),
            gender,
            email = sharedPreferences.getString("email", null),
            birthDate
        );

        repository.updateProfile(
            newProfile,
            onSuccess = {
                context.runOnUiThread {
                    val editor = sharedPreferences.edit()
                    editor.putString("name", name)
                    editor.putString("gender", gender)
                    editor.putString("birthDate", birthDate)
                    editor.apply()

                    Toast.makeText(context, "Perfil atualizado com sucesso", Toast.LENGTH_SHORT).show()

                    onSubmit()
                }
            },
            onFailure = { error ->
                context.runOnUiThread {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()

                    onSubmit()
                }
            }
        )
    }
}