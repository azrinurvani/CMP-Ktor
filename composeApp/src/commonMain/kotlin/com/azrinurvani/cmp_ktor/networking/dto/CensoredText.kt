package com.azrinurvani.cmp_ktor.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CensoredText(
    val result : String
)