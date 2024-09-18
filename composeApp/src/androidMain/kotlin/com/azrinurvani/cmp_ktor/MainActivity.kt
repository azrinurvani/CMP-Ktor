package com.azrinurvani.cmp_ktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.azrinurvani.cmp_ktor.networking.InsultCensorClient
import com.azrinurvani.cmp_ktor.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                client = remember{
                    InsultCensorClient(createHttpClient(OkHttp.create()))
                }
            )
        }
    }
}