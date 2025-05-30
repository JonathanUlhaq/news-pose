package id.prj.fl.newspose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import id.prj.fl.newspose.R

val newsFontFamily:FontFamily = FontFamily(
    Font(R.font.newsreader_bold, weight = FontWeight.Bold),
    Font(R.font.newsreader_regular, weight = FontWeight.Normal)
)
// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        color = Color.Black
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = Color.Gray
    ),
    headlineLarge = TextStyle(
        fontFamily = newsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = DarkBlue
    ),
    headlineSmall = TextStyle(
        fontFamily = newsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = DarkBlue
    ),
    labelLarge = TextStyle(
        fontFamily = newsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = DarkBlue
    ),
    bodyLarge = TextStyle(
        fontFamily = newsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = BlueOpal
    ),
    bodyMedium = TextStyle(
        fontFamily = newsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = BlueOpal
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)