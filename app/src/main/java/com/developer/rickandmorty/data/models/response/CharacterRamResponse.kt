package com.developer.rickandmorty.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterRamResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("species")
    val species: String,
    @SerialName("type")
    val type: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("location")
    val location: CharacterLocationResponse,
    @SerialName("image")
    val image: String,
    @SerialName("url")
    val url: String,
    @SerialName("created")
    val created: String,
)