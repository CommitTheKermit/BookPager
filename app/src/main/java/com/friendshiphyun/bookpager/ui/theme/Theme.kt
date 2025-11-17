package com.friendshiphyun.bookpager.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Light 테마 색상 정의 (Material3 ColorScheme)
 * Dark 테마는 사용 X
 */
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1E4A75),           // 메인 색상 (다크 블루)
    onPrimary = Color(0xFFFFFFFF),         // Primary 위의 텍스트 (흰색)
    primaryContainer = Color(0xFFDDDFEC),  // Primary 연한 버전
    onPrimaryContainer = Color(0xFF363639),

    secondary = Color(0xFF5A6B7D),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDFE7F1),
    onSecondaryContainer = Color(0xFF1A2A39),

    background = Color(0xFFFFFFFF),        // 배경색
    onBackground = Color(0xFF363639),      // 배경 위의 텍스트

    surface = Color(0xFFF2F3F5),           // 표면색
    onSurface = Color(0xFF363639),         // 표면 위의 텍스트

    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),

    outline = Color(0xFFF2F3F5),           // 테두리/구분선
)

@Composable
fun BookPagerTheme(
    useDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        shapes = Shapes(),
        content = content
    )
}
