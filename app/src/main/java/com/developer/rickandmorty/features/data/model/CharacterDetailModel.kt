package com.developer.rickandmorty.features.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDetailModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    var isFavorite: Boolean = false,
):Parcelable
