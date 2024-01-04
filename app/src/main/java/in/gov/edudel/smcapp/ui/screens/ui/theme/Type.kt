package `in`.gov.edudel.smcapp.ui.screens.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import `in`.gov.edudel.smcapp.R

// Set of Material typography styles to start with

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val robotofont = GoogleFont("Roboto")

val roboto = FontFamily(
    Font(googleFont = robotofont, fontProvider = provider)
)


val x = TextStyle(

    fontFamily = roboto,
    fontWeight = FontWeight.Black,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
)
val Typography = Typography(

    displayLarge = x,
    displayMedium = x,
    displaySmall = x,

    bodyLarge = x,
    bodyMedium = x,
    bodySmall = x,

    headlineLarge = x,
    headlineMedium = x,
    headlineSmall = x,

    labelLarge = x,
    labelMedium = x,
    labelSmall = x,

    titleLarge = x,
    titleMedium = x,
    titleSmall = x

//
//    ,
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Monospace,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//    /* Other default text styles to override
//    titleLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
//    */
)