package dev.ericknathan.ampz.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.PowerInput
import androidx.compose.ui.graphics.vector.ImageVector

data class DeviceType(
    val value: String,
    val label: String,
    val icon: ImageVector? = null
) {
    companion object {
        val AMPZ = DeviceType("ampz", "Ampz", Icons.Default.Power)
        val LIGHT = DeviceType("light", "Luz", Icons.Default.Lightbulb)
        val OUTLET = DeviceType("outlet", "Tomada", Icons.Default.PowerInput)
        val OTHER = DeviceType("other", "Outro", Icons.Default.Devices)

        fun values() = arrayOf(AMPZ, LIGHT, OUTLET, OTHER)
    }
}

data class Device(val id: Int?, val name: String, val type: String = DeviceType.OTHER.value) {
    val typeObject: DeviceType
        get() = when (type) {
            DeviceType.AMPZ.value -> DeviceType.AMPZ
            DeviceType.LIGHT.value -> DeviceType.LIGHT
            DeviceType.OUTLET.value -> DeviceType.OUTLET
            else -> DeviceType.OTHER
        }
}
