package dev.ericknathan.ampz.controllers

import android.widget.Toast
import androidx.activity.ComponentActivity
import dev.ericknathan.ampz.models.Challenge
import dev.ericknathan.ampz.models.RankingUser
import dev.ericknathan.ampz.repositories.ConsumptionRepository

class ConsumptionController(private val context: ComponentActivity) {
    private val repository = ConsumptionRepository()

    fun getSevenDaysConsumption(id: Int, onSubmit: (List<Double>) -> Unit) {
        repository.getSevenDaysConsumption(
            id,
            onSuccess = { consumption ->
                context.runOnUiThread {
                    onSubmit(consumption)
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

    fun getCommunityRanking(id: Int, onSubmit: (List<RankingUser>) -> Unit) {
        repository.getCommunityRanking(
            id,
            onSuccess = { ranking ->
                context.runOnUiThread {
                    onSubmit(ranking)
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

    fun getChallenges(id: Int, onSubmit: (List<Challenge>) -> Unit) {
        repository.getChallenges(
            id,
            onSuccess = { challenges ->
                context.runOnUiThread {
                    onSubmit(challenges)
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