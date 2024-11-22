package dev.ericknathan.ampz.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.ericknathan.ampz.R

val poppinsFamily = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_extrabold, FontWeight.ExtraBold)
)

private val defaultTypography = Typography()
val Typography = Typography(
    titleLarge = defaultTypography.titleLarge.copy(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp
    ),
    titleMedium = defaultTypography.titleMedium.copy(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    ),
    titleSmall = defaultTypography.titleSmall.copy(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),

    bodySmall = defaultTypography.bodySmall.copy(fontFamily = poppinsFamily, fontSize = 14.sp),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = poppinsFamily, fontSize = 16.sp),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = poppinsFamily, fontSize = 18.sp),

    labelSmall = defaultTypography.labelSmall.copy(fontFamily = poppinsFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = poppinsFamily),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = poppinsFamily)
)