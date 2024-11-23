package dev.ericknathan.ampz.ui.activities.pages

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import dev.ericknathan.ampz.ui.theme.AmpzTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.ericknathan.ampz.controllers.DeviceController
import dev.ericknathan.ampz.models.Challenge
import dev.ericknathan.ampz.models.Device
import dev.ericknathan.ampz.models.DeviceType
import dev.ericknathan.ampz.ui.components.Card
import dev.ericknathan.ampz.ui.components.FormButton
import dev.ericknathan.ampz.ui.components.FormButtonSecondary
import dev.ericknathan.ampz.ui.components.FormField
import dev.ericknathan.ampz.ui.components.Header
import dev.ericknathan.ampz.ui.components.Skeleton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DevicesPage() {
    val context = LocalContext.current as ComponentActivity
    val controller = remember { DeviceController(context) }
    val user = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    val userId = user.getInt("id", 0)

    var isModalOpen by remember { mutableStateOf(false) }
    var selectedDevice by remember { mutableStateOf<Device?>(null) }

    var devices = remember { mutableStateListOf<Device>() }
    LaunchedEffect(userId) {
        controller.getDevices(
            id = userId,
            onSubmit = { deviceList ->
                devices.addAll(deviceList)
            }
        )
    }

    AmpzTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        selectedDevice = null
                        isModalOpen = true
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar dispositivo")
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = {
                Spacer(modifier = Modifier.size(0.dp))
            }
        ) { _ ->
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Header(
                    text = "Dispositivos conectados",
                    modifier = Modifier.padding(bottom = 16.dp, top = 24.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if(devices.isNotEmpty()) {
                        items(devices.size) { index ->
                            DeviceCard(
                                device = devices[index],
                                onEdit = {
                                    selectedDevice = it
                                    isModalOpen = true
                                },
                                onDelete = {
                                    controller.deleteDevice(
                                        id = userId,
                                        device = it,
                                        onSubmit = {
                                            devices.remove(it)
                                        }
                                    )
                                }
                            )
                        }
                    } else {
                        items(6) {
                            Skeleton(
                                modifier = Modifier.height(250.dp)
                            )
                        }
                    }
                }
            }

            if (isModalOpen) {
                DeviceModal(
                    device = selectedDevice,
                    onDismiss = {
                        isModalOpen = false
                        selectedDevice = null
                    },
                    onSave = { updatedDevice ->
                        if(selectedDevice != null) {
                            Log.d("DevicesPage", "Updating device: $updatedDevice")
                            controller.updateDevice(
                                id = userId,
                                device = updatedDevice,
                                onSubmit = {
                                    devices[devices.indexOf(selectedDevice!!)] = updatedDevice
                                    isModalOpen = false
                                }
                            )
                        } else {
                            Log.d("DevicesPage", "Saving device: $updatedDevice")
                            controller.saveDevice(
                                id = userId,
                                device = updatedDevice,
                                onSubmit = {
                                    devices.add(updatedDevice)
                                    isModalOpen = false
                                }
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DeviceCard(device: Device, onEdit: (Device) -> Unit, onDelete: (Device) -> Unit) {
    Card(title = device.name) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = device.typeObject.icon ?: Icons.Default.Devices,
                contentDescription = "Dispositivo",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            FormButton(onClick = { onEdit(device) }, text = "Editar")
            FormButtonSecondary(onClick = { onDelete(device) }, text = "Excluir")
        }
    }
}

@Composable
fun DeviceModal(
    device: Device?,
    onDismiss: () -> Unit,
    onSave: (Device) -> Unit
) {
    var name by remember { mutableStateOf(device?.name ?: "") }
    var type by remember { mutableStateOf(device?.type ?: DeviceType.AMPZ.value) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = if (device == null) "Novo Dispositivo" else "Editar Dispositivo",
                    style = MaterialTheme.typography.titleMedium
                )

                FormField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Nome do dispositivo",
                )

                TypeSelection(
                    selectedType = DeviceType.values().find { it.value == type },
                    onSelectedChanged = {
                        type = it.value
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                    TextButton(
                        onClick = {
                            if (name.isNotBlank()) {
                                onSave(device?.copy(name = name, type = type) ?: Device(null, name, type))
                            }
                        }
                    ) {
                        Text("Salvar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TypeSelection(
    types: List<DeviceType> = listOf(
        DeviceType.AMPZ,
        DeviceType.LIGHT,
        DeviceType.OUTLET,
        DeviceType.OTHER
    ),
    selectedType: DeviceType? = null,
    onSelectedChanged: (DeviceType) -> Unit = {},
) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(types.size) {
                FilterChip(
                    label = { Text(types[it].label) },
                    selected = types[it].label == selectedType?.label,
                    onClick = {
                        onSelectedChanged(types[it])
                    },
                    leadingIcon = if (types[it].label == selectedType?.label) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    }
                )
            }
        }
    }
}
