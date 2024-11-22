package dev.ericknathan.ampz.utils

import java.util.Calendar

class Validators {
    companion object {
        fun isEmailValid(email: String): Boolean {
            return email.contains("@") && email.contains(".")
        }

        fun isPasswordValid(password: String): Boolean {
            return password.length >= 8
        }

        fun isNameValid(name: String): Boolean {
            return name.length >= 3
        }

        fun isBirthDateValid(birthDate: String): Boolean {
            val splitDate = birthDate.split("/");
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)

            return birthDate.length == 10 &&
                    birthDate.contains("/") &&
                    birthDate.split("/").size == 3 &&
                    splitDate[0].toInt() in 1..31 &&
                    splitDate[1].toInt() in 1..12 &&
                    splitDate[2].toInt() in 1900..currentYear
        }
    }
}