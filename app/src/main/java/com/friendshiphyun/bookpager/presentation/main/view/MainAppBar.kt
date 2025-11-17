package com.friendshiphyun.bookpager.presentation.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ErrorOutline
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.friendshiphyun.bookpager.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    onRefresh: () -> Unit,
    isEnabled: Boolean = false,
) {
    Column {
        TopAppBar(title = {
            Text(
                "페이져",
                color = Colors.primaryMain,
                fontWeight = FontWeight.ExtraBold
            )
        }, actions = {
            // 연결 상태 표시
            Box(
                modifier = Modifier
                    .background(
                        color = if(isEnabled) Colors.primaryMain else Color.Red,
                        shape = CircleShape
                    )
                    .padding(4.dp)
                    .size(20.dp)
            ) {
                Icon(
                    imageVector = if (isEnabled) Icons.Default.Check else Icons.Default.ErrorOutline,
                    contentDescription = "연결 상태",
                    tint = Colors.commonWhite,
                )
            }

            IconButton(onClick = { onRefresh() }) {
                Icon(Icons.Filled.Refresh, contentDescription = "상태 확인")
            }
        })
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.shadow(elevation = 2.dp)

        )
    }
}