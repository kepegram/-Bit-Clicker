package com.example.gameappdev.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gameappdev.R

private val DMSans = FontFamily(
    //Regular
    Font(R.font.dmsans_regular, FontWeight.Normal),
    Font(R.font.dmsans_medium, FontWeight.Medium),
    Font(R.font.dmsans_bold, FontWeight.Bold),

    //Italics
    Font(R.font.dmsans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.dmsans_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.dmsans_bold_italic, FontWeight.Bold, FontStyle.Italic),

    )

internal object Fonts {
    val Title = TextStyle(
        fontFamily = DMSans,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 26.sp,
        fontFeatureSettings = "pnum, lnum",
    )

    val Body = TextStyle(
        fontFamily = DMSans,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        fontFeatureSettings = "pnum, lnum",
    )

    val Caption = TextStyle(
        fontFamily = DMSans,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        fontFeatureSettings = "pnum, lnum",
    )
}

val AppTypography = Typography(
    h1 = Fonts.Title,
    h2 = Fonts.Title,
    h3 = Fonts.Title,
    h4 = Fonts.Title,
    h5 = Fonts.Title,
    h6 = Fonts.Title,
    subtitle1 = Fonts.Title,
    subtitle2 = Fonts.Title,
    body1 = Fonts.Body,
    body2 = Fonts.Body,
    button = Fonts.Body,
    caption = Fonts.Caption,
    overline = Fonts.Caption
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)