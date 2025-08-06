package com.developer.rickandmorty.features.data.remote.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterLocationResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)