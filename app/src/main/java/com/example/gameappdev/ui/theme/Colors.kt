package com.example.gameappdev.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkBlue = Color.fromRGB("#0B1729")
val Silver20 = Color.fromRGB("#E5E5E5")
val SilverLight = Color.fromRGB("#F8F7F8")
val White = Color.fromRGB("#FFFFFF")
val YellowyColor = Color(0xFF92861d)
val MistyColor = Color(0xFFD8E1E7)

@Composable
fun backgroundColor() = DarkBlue orInLightTheme SilverLight

@Composable
fun captionColor() = Silver20 orInLightTheme DarkBlue

private fun Color.Companion.fromRGB(rgb: String) = Color(android.graphics.Color.parseColor(rgb))

@SuppressLint("ConflictingOnColor")
val AppLightColors = lightColors(
  primary = YellowyColor,
  secondary = DarkBlue,
  background = MistyColor,
  surface = White,
  onPrimary = White,
  onBackground = DarkBlue,
  onSecondary = White,
)

@SuppressLint("ConflictingOnColor")
val AppDarkColors = darkColors(
  primary = YellowyColor,
  secondary = White,
  background = DarkBlue,
  surface = DarkBlue,
  onPrimary = DarkBlue,
  onBackground = Silver20,
  onSecondary = DarkBlue,
)