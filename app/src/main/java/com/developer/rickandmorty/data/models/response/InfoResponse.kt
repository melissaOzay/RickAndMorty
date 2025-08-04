package com.developer.rickandmorty.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoResponse(
    @SerialName("next")
    val next: String? = null,
)