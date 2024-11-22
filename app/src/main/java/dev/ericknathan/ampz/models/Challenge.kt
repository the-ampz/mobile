package dev.ericknathan.ampz.models

data class Challenge(
    val title: String,
    val description: String,
    val progress: Float,
    val reward: String,
    val completed: Boolean
)