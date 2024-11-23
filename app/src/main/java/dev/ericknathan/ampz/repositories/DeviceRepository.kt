package dev.ericknathan.ampz.repositories

import com.google.gson.Gson
import dev.ericknathan.ampz.models.Device
import dev.ericknathan.ampz.utils.Constants
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class DeviceRepository {
    private val httpClient = OkHttpClient()
    val gson = Gson()

    fun saveDevice(id: Int, device : Device, onSuccess: () -> Unit, onFailure: (error: String) -> Unit) {
        val formJson = gson.toJson(device)
        val request = Request.Builder()
            .url("${Constants.API_URL}/$id/devices")
            .post(formJson.toRequestBody("application/json".toMediaType()))
            .build()

        val response = object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                onFailure("Erro ao salvar dispositivo")
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

    fun updateDevice(id: Int, device : Device, onSuccess: () -> Unit, onFailure: (error: String) -> Unit) {
        val formJson = gson.toJson(device)
        val request = Request.Builder()
            .url("${Constants.API_URL}/$id/devices/${device.id}")
            .put(formJson.toRequestBody("application/json".toMediaType()))
            .build()

        val response = object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                onFailure("Erro ao atualizar dispositivo")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if(response.code == 404) {
                    onFailure("Dispositivo não encontrado")
                    return
                }

                onSuccess()
            }
        }

        httpClient.newCall(request).enqueue(response)
    }

    fun deleteDevice(id: Int, deviceId: Int, onSuccess: () -> Unit, onFailure: (error: String) -> Unit) {
        val request = Request.Builder()
            .url("${Constants.API_URL}/$id/devices/$deviceId")
            .delete()
            .build()

        val response = object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                onFailure("Erro ao deletar dispositivo")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if(response.code == 404) {
                    onFailure("Dispositivo não encontrado")
                    return
                }

                onSuccess()
            }
        }

        httpClient.newCall(request).enqueue(response)
    }

    fun getDevices(id: Int, onSuccess: (devices: List<Device>) -> Unit, onFailure: (error: String) -> Unit) {
        val request = Request.Builder()
            .url("${Constants.API_URL}/$id/devices")
            .get()
            .build()

        val response = object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                onFailure("Erro ao buscar dispositivos")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if(response.code == 404) {
                    onFailure("Usuário não encontrado")
                    return
                }

                val devices = gson.fromJson(response.body?.string(), Array<Device>::class.java).toList()
                onSuccess(devices)
            }
        }

        httpClient.newCall(request).enqueue(response)
    }
}