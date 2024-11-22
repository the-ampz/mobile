package dev.ericknathan.ampz.models

import dev.ericknathan.ampz.R

enum class Gender {
    BOY, GIRL
}

data class User(
    val name: String,
    val score : Int,
    val gender : Gender
)

fun getUserAvatar(gender : Gender): Int {
    return when (gender) {
        Gender.BOY -> R.drawable.avatar_boy
        else -> R.drawable.avatar_girl
    }
}