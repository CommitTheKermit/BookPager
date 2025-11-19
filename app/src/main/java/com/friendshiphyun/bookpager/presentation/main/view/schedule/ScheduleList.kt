package com.friendshiphyun.bookpager.presentation.main.view.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.friendshiphyun.bookpager.domain.model.Schedule
import com.friendshiphyun.bookpager.ui.theme.Colors
import java.time.LocalTime

@Composable
fun ScheduleList(
    schedules: List<Schedule> = emptyList(),
    onDialog: () -> Unit = {},
    onToggle: (Schedule) -> Unit,
    onDelete: (Schedule) -> Unit,
) {
    Column(
        modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("페이지 턴 일정")
            Button(
                onClick = { onDialog() },
                modifier = Modifier.height(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.primaryMain
                ),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    "추가",
                )
            }

        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            schedules.forEach { schedule ->
                SingleScheduleItem(
                    schedule = schedule,
                    onToggle = {
                        onToggle(schedule)
                    },
                    onDelete = {
                        onDelete(schedule)
                    }
                )
            }
        }

    }

}


