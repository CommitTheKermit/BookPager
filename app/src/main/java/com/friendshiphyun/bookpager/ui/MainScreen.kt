package com.friendshiphyun.bookpager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.friendshiphyun.bookpager.ui.theme.commonWhite
import com.friendshiphyun.bookpager.ui.theme.primaryMain
import com.friendshiphyun.bookpager.ui.theme.primarySub

/**
 * 메인 BookFrame 화면
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            MainAppBar()
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .background(
                        color = primarySub, shape = RoundedCornerShape(12.dp)
                    )
            )
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.MenuBook,
                        tint = primaryMain,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)

                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 10.dp, vertical = 20.dp)
                            .background(
                                color = primaryMain, shape = RoundedCornerShape(16.dp)
                            )
                            .height(40.dp)
                            .width(100.dp)
                    )
                    {
                        Text("페이지 넘기기", color = commonWhite)
                    }

                }
            }
        }
    }
}