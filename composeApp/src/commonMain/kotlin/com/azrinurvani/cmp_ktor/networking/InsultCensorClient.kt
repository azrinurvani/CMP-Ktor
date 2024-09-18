package com.azrinurvani.cmp_ktor.networking

import com.azrinurvani.cmp_ktor.networking.dto.CensoredText
import com.azrinurvani.cmp_ktor.util.NetworkError
import com.azrinurvani.cmp_ktor.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class InsultCensorClient(
    private val httpClient : HttpClient
) {

    suspend fun censorWords(uncensored : String) : Result<String,NetworkError>{
        val response = try {
            httpClient.get(
                urlString = "https://www.purgomalum.com/service/json"
            ){
                //pass uncensored param to request api
                parameter("text",uncensored) //text is name of parameter from api request
            }
        }catch (e : UnresolvedAddressException){
            return Result.Error(NetworkError.NO_INTERNET)
        }catch (e : SerializationException){
            return Result.Error(NetworkError.SERIALIZATION)
        }

        /*
        * {"result:" : "You ****"}
        * */

        return when(response.status.value){
            in 200..299 ->{
                val censoredText = response.body<CensoredText>()
                Result.Success(censoredText.result)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }

    }
}