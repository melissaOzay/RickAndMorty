package com.developer.rickandmorty.features.data.remote.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("error")
    val error: String? = "",
    @SerialName("info")
    val info: InfoResponse,
    @SerialName("results")
    val characterRams: List<CharacterRamResponse>,
)