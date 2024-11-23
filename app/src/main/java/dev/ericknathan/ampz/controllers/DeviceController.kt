package dev.ericknathan.ampz.controllers

import android.util.Log
import dev.ericknathan.ampz.repositories.DeviceRepository
import android.widget.Toast
import androidx.activity.ComponentActivity
import dev.ericknathan.ampz.models.Device

class DeviceController(private val context: ComponentActivity) {
    private val repository = DeviceRepository()

    fun saveDevice(id: Int, device: Device, onSubmit: () -> Unit) {
        repository.saveDevice(
            id,
            device,
            onSuccess = {
                context.runOnUiThread {
                    Toast.makeText(context, "Dispositivo salvo com sucesso", Toast.LENGTH_SHORT).show()
                    onSubmit()
                }
            },
            onFailure = { error ->
                context.runOnUiThread {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun updateDevice(id: Int, device: Device, onSubmit: () -> Unit) {
        repository.updateDevice(
            id,
            device,
            onSuccess = {
                context.runOnUiThread {
                    Toast.makeText(context, "Dispositivo atualizado com sucesso", Toast.LENGTH_SHORT).show()
                    onSubmit()
                }
            },
            onFailure = { error ->
                context.runOnUiThread {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun deleteDevice(id: Int, device: Device, onSubmit: () -> Unit) {
        Log.d("DeviceController", "deleteDevice: ${id} ${device.id}")
        repository.deleteDevice(
            id,
            deviceId = device.id!!,
            onSuccess = {
                context.runOnUiThread {
                    Toast.makeText(context, "Dispositivo excluído com sucesso", Toast.LENGTH_SHORT).show()
                    onSubmit()
                }
            },
            onFailure = { error ->
                context.runOnUiThread {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun getDevices(id: Int, onSubmit: (List<Device>) -> Unit) {
        repository.getDevices(
            id,
            onSuccess = { devices ->
                context.runOnUiThread {
                    onSubmit(devices)
                }
            },
            onFailure = { error ->
                context.runOnUiThread {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    onSubmit(listOf())
                }
            }
        )
    }
}