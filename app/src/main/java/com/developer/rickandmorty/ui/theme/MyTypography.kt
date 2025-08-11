package com.developer.rickandmorty.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun getMyTypography(): Typography {
    return Typography(
    bodyLarge = TextStyle(
        fontSize = 16.ssp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Serif
    ),
    bodyMedium = TextStyle(
        fontSize = 14.ssp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Serif

    ),
    bodySmall = TextStyle(
        fontSize = 12.ssp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Serif

    ),
    titleLarge = TextStyle(
        fontSize = 20.ssp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif

    ),
    titleMedium = TextStyle(
        fontSize = 18.ssp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif

    ),
    titleSmall = TextStyle(
        fontSize = 16.ssp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif

    )
)}