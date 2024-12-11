package com.fueled.technicalchallenge.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fueled.technicalchallenge.R

private val Urbanist_Regular = Font(R.font.urbanist_regular, FontWeight.Normal)
private val Urbanist_Medium = Font(R.font.urbanist_medium, FontWeight.Medium)
private val Urbanist_Semibold = Font(R.font.urbanist_semibold, FontWeight.SemiBold)
private val Urbanist_Bold = Font(R.font.urbanist_bold, FontWeight.Bold)

private val urbanistFontFamily = FontFamily(fonts =  listOf(
    Urbanist_Regular, Urbanist_Medium, Urbanist_Semibold, Urbanist_Bold,
))

// Set of Material typography styles to start with
val TechnicalChallengeTypography = Typography(

    displayLarge = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    ),

    headlineLarge = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),

    titleLarge = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),

    bodyLarge = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),

    labelLarge = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    ),
)