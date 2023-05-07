package ru.alexpanov.spacex

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.fontFamilyResource

private val DarkColors = darkColors(background = Color.Black)

private val lineHeightStyle = LineHeightStyle(
    alignment = LineHeightStyle.Alignment.Center,
    trim = LineHeightStyle.Trim.None
)

val Colors.textSecondary
    @Composable
    get() = Color(0xFF8E8E8F)

val Colors.textTertiary
    @Composable
    get() = Color(0xFFCACACA)

val Colors.cardPrimary
    @Composable
    get() = Color(0xFF212121)

val Colors.pagerIndicatorBackground
    @Composable
    get() = Color(0xFF121212)

@Composable
fun rememberTypography(): Typography {
    val regularFontFamily = fontFamilyResource(MR.fonts.LabGrotesque.regular)
    val boldFontFamily = fontFamilyResource(MR.fonts.LabGrotesque.bold)
    return remember {
        Typography(
            body1 = TextStyle(
                fontFamily = regularFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                lineHeightStyle = lineHeightStyle
            ),
            body2 = TextStyle(
                fontFamily = regularFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                lineHeightStyle = lineHeightStyle
            ),
            subtitle1 = TextStyle(
                fontFamily = boldFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                lineHeightStyle = lineHeightStyle
            ),
            h6 = TextStyle(
                fontFamily = regularFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                lineHeightStyle = lineHeightStyle
            ),
            h5 = TextStyle(
                fontFamily = regularFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                lineHeightStyle = lineHeightStyle
            ),
            button = TextStyle(
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
        colors = DarkColors,
        typography = rememberTypography(),
        content = {
            Surface(
                content = content,
                color = MaterialTheme.colors.background
            )
        }
    )
}
