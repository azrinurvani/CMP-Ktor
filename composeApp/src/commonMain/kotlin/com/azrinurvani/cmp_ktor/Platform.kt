package com.azrinurvani.cmp_ktor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform