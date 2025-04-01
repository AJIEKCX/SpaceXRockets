package ru.alexpanov.spacex.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource

private val DarkColorScheme = darkColorScheme(
    background = Color.Black
)

private val lineHeightStyle = LineHeightStyle(
    alignment = LineHeightStyle.Alignment.Center,
    trim = LineHeightStyle.Trim.None
)

val ColorScheme.textSecondary
    @Composable
    get() = Color(0xFF8E8E8F)

val ColorScheme.textTertiary
    @Composable
    get() = Color(0xFFCACACA)

val ColorScheme.cardPrimary
    @Composable
    get() = Color(0xFF212121)

val ColorScheme.pagerIndicatorBackground
    @Composable
    get() = Color(0xFF121212)

@Composable
fun rememberTypography(): Typography {
    val regularFontFamily = fontFamilyResource(LabGrotesqueFont.regular)
    val boldFontFamily = fontFamilyResource(LabGrotesqueFont.bold)
    return remember {
        Typography(
            bodyLarge = TextStyle(
                fontFamily = regularFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                lineHeightStyle = lineHeightStyle
            ),
            bodyMedium = TextStyle(
                fontFamily = regularFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                lineHeightStyle = lineHeightStyle
            ),
            titleMedium = TextStyle(
                fontFamily = boldFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                lineHeightStyle = lineHeightStyle
            ),
            headlineSmall = TextStyle(
                fontFamily = regularFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                lineHeightStyle = lineHeightStyle
            ),
            headlineMedium = TextStyle(
                fontFamily = regularFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                lineHeightStyle = lineHeightStyle
            ),
            labelMedium = TextStyle(
                fontFamily = boldFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                lineHeightStyle = lineHeightStyle
            ),
        )
    }
}

@Composable
internal fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = rememberTypography(),
        content = {
            Surface(
                content = content,
                color = MaterialTheme.colorScheme.background
            )
        }
    )
}
