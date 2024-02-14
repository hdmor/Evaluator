package m.eight.evaluator.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import m.eight.evaluator.R

val QuickStand = FontFamily(
    Font(R.font.bold, FontWeight.Bold),
    Font(R.font.light, FontWeight.Light),
    Font(R.font.medium, FontWeight.Medium),
    Font(R.font.regular, FontWeight.Normal),
    Font(R.font.semi_bold, FontWeight.SemiBold)
)

val Typography = Typography(
    displaySmall = TextStyle(
        fontFamily = QuickStand,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = QuickStand,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    labelSmall = TextStyle(
        fontFamily = QuickStand,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp
    ),
    titleSmall = TextStyle(
        fontFamily = QuickStand,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    titleMedium = TextStyle(
        fontFamily = QuickStand,
        fontWeight = FontWeight.Bold,
        letterSpacing = (.5).sp,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle(
        fontFamily = QuickStand,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    )
)