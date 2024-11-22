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

data class Profile(
    val id : Int,
    val name: String,
    val score : Int,
    val gender : String,
    val email : String? = null,
    val birthDate : String? = null
)

data class RankingUser(
    val position: Int,
    val user: User
)

fun getUserAvatar(gender : Gender): Int {
    return when (gender) {
        Gender.BOY -> R.drawable.avatar_boy
        else -> R.drawable.avatar_girl
    }
}