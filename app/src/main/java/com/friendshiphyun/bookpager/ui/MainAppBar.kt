package com.friendshiphyun.bookpager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.friendshiphyun.bookpager.ui.theme.commonWhite
import com.friendshiphyun.bookpager.ui.theme.primaryMain

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainAppBar() {
    Column {
        TopAppBar(title = {
            Text(
                "페이져",
                color = primaryMain,
                fontWeight = FontWeight.ExtraBold
            )
        }, actions = {
            // 연결 상태 표시
            Box(
                modifier = Modifier
                    .background(
                        color = primaryMain,
                        shape = CircleShape
                    )
                    .padding(4.dp)
                    .size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "연결 상태",
                    tint = commonWhite,
                )
            }

            IconButton(onClick = { }) {
                Icon(Icons.Filled.Refresh, contentDescription = "상태 확인")
            }
        })
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.shadow(elevation = 2.dp)

        )
    }
}