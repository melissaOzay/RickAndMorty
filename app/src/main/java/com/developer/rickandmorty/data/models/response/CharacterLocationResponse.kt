package com.developer.rickandmorty.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterLocationResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)