package dev.ericknathan.ampz.repositories

import android.util.Log
import com.google.gson.Gson
import dev.ericknathan.ampz.models.Profile
import dev.ericknathan.ampz.utils.Constants
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class AuthRepository {
    private val httpClient = OkHttpClient()
    val gson = Gson()

    fun signIn(email: String, password: String, onSuccess: (profile: Profile) -> Unit, onFailure: (error: String) -> Unit) {
        val formJson = gson.toJson(mapOf("email" to email, "password" to password))
        val request = Request.Builder()
            .url("${Constants.API_URL}/auth/signin")
            .post(formJson.toRequestBody("application/json".toMediaType()))
            .build()

        val response = object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                onFailure("Erro ao realizar login")
                Log.e("AuthRepository", "Error on sign in", e)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if(response.code == 401) {
                    onFailure("E-mail ou senha inválidos")
                    return
                }

                val profile = gson.fromJson(response.body?.string(), Profile::class.java)
                onSuccess(profile)
            }
        }

        httpClient.newCall(request).enqueue(response)
    }

    fun recoverPassword(email: String, onSuccess: () -> Unit, onFailure: (error: String) -> Unit) {
        val formJson = gson.toJson(mapOf("email" to email))
        val request = Request.Builder()
            .url("${Constants.API_URL}/auth/recover")
            .post(formJson.toRequestBody("application/json".toMediaType()))
            .build()

        val response = object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                onFailure("Erro ao recuperar senha")
                Log.e("AuthRepository", "Error on recover password", e)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if(response.code == 404) {
                    onFailure("Cadastro não cadastrado")
                    return
                }

                onSuccess()
            }
        }

        httpClient.newCall(request).enqueue(response)
    }

    fun updateProfile(profile : Profile, onSuccess: () -> Unit, onFailure: (error: String) -> Unit) {
        val formJson = gson.toJson(profile)
        val request = Request.Builder()
            .url("${Constants.API_URL}/auth/update/${profile.id}")
            .put(formJson.toRequestBody("application/json".toMediaType()))
            .build()

        val response = object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                onFailure("Erro ao atualizar perfil")
                Log.e("AuthRepository", "Error on update profile", e)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if(response.code == 404) {
                    onFailure("Usuário não encontrado")
                    return
                }

                onSuccess()
            }
        }

        httpClient.newCall(request).enqueue(response)
    }
}