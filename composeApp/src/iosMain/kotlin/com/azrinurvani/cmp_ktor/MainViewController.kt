package com.azrinurvani.cmp_ktor

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.azrinurvani.cmp_ktor.networking.InsultCensorClient
import com.azrinurvani.cmp_ktor.networking.createHttpClient
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController { App(
    client = remember{
        InsultCensorClient(createHttpClient(Darwin.create()))
    }
) }