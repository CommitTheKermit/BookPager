package com.friendshiphyun.bookpager.presentation.main.view.manualControl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.friendshiphyun.bookpager.ui.theme.Colors

@Composable
fun ManualControl(
    onMotorControl: (motor: Int, forward: Boolean) -> Unit,
    isLoading: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 20.dp)
            .background(
                color = Colors.primarySub,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = Colors.primaryMain,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "모터 수동 제어",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Colors.primaryMain,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            SingleMotorItem(
                motorName = "리프터",
                onForward = { onMotorControl(1, true) },
                onBackward = { onMotorControl(1, false) },
                isLoading = isLoading
            )

            // 모터 2 (360도)
            SingleMotorItem(
                motorName = "피니언 랙",
                onForward = { onMotorControl(2, true) },
                onBackward = { onMotorControl(2, false) },
                isLoading = isLoading
            )

            // 모터 3 (360도)
            SingleMotorItem(
                motorName = "페이지 턴",
                onForward = { onMotorControl(3, true) },
                onBackward = { onMotorControl(3, false) },
                isLoading = isLoading
            )
        }
    }
}


