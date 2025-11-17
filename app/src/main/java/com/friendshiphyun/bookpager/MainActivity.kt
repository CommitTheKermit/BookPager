package com.friendshiphyun.bookpager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.friendshiphyun.bookpager.presentation.main.view.MainScreen
import com.friendshiphyun.bookpager.ui.theme.BookPagerTheme

/**
 * BookPager - 자동 액자 IoT 프로젝트
 * ESP32와 WiFi로 통신하여 책 페이지를 자동으로 넘기는 앱
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookPagerTheme {
                MainScreen()
            }

        }
    }
}