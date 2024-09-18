package com.azrinurvani.cmp_ktor

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.azrinurvani.cmp_ktor.networking.InsultCensorClient
import com.azrinurvani.cmp_ktor.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMP-Ktor",
    ) {
        App(
            client = remember{
                InsultCensorClient(createHttpClient(OkHttp.create()))
            }
        )
    }
}