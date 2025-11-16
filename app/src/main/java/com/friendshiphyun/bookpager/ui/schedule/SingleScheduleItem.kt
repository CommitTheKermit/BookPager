package com.friendshiphyun.bookpager.ui.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.friendshiphyun.bookpager.model.Schedule
import com.friendshiphyun.bookpager.ui.theme.Colors

@Composable
fun SingleScheduleItem(
    schedule: Schedule,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = { }
            )
            Text(
                schedule.getDisplayTime(),
                color = Colors.primaryMain,
                style = MaterialTheme.typography.titleMedium,
            )
        }
        IconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = Colors.primaryMain,
                contentDescription = "일정 삭제"
            )

        }
    }

}