package com.vs.august_2025_challenge.thermometerTrek

sealed interface ThermometerAction {
    object OnClickStart : ThermometerAction
    object OnResetClick : ThermometerAction
}