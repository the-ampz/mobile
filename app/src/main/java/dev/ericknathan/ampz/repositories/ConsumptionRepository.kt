package dev.ericknathan.ampz.repositories

import com.google.gson.Gson
import dev.ericknathan.ampz.models.Challenge
import dev.ericknathan.ampz.models.RankingUser
import dev.ericknathan.ampz.utils.Constants
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class ConsumptionRepository {
    private val httpClient = OkHttpClient()
    val gson = Gson()

    fun getSevenDaysConsumption(id: Int, onSuccess: (List<Double>) -> Unit, onFailure: (error: String) -> Unit) {
        val request = Request.Builder()
            .url("${Constants.API_URL}/consumption/$id")
            .get()
            .build()

        val response = object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                onFailure("Erro ao buscar consumo dos últimos 7 dias")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if(response.code == 404) {
                    onFailure("Consumo não encontrado")
                    return
                }

                val consumption = gson.fromJson(response.body?.string(), Array<Double>::class.java).toList()
                onSuccess(consumption)
            }
        }

        httpClient.newCall(request).enqueue(response)
    }

    fun getCommunityRanking(id: Int, onSuccess: (List<RankingUser>) -> Unit, onFailure: (error: String) -> Unit) {
        val request = Request.Builder()
            .url("${Constants.API_URL}/consumption/ranking/$id")
            .get()
            .build()

        val response = object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                onFailure("Erro ao buscar ranking da comunidade")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if(response.code == 404) {
                    onFailure("Ranking não encontrado")
                    return
                }

                val ranking = gson.fromJson(response.body?.string(), Array<RankingUser>::class.java).toList()
                onSuccess(ranking)
            }
        }

        httpClient.newCall(request).enqueue(response)
    }

    fun getChallenges(id: Int, onSuccess: (List<Challenge>) -> Unit, onFailure: (error: String) -> Unit) {
        val request = Request.Builder()
            .url("${Constants.API_URL}/consumption/challenges/$id")
            .get()
            .build()

        val response = object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                onFailure("Erro ao buscar desafios")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if(response.code == 404) {
                    onFailure("Desafios não encontrados")
                    return
                }

                val challenges = gson.fromJson(response.body?.string(), Array<Challenge>::class.java).toList()
                onSuccess(challenges)
            }
        }

        httpClient.newCall(request).enqueue(response)
    }
}