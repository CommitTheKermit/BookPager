package com.friendshiphyun.bookpager.presentation.main.view.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.friendshiphyun.bookpager.domain.model.Schedule
import com.friendshiphyun.bookpager.ui.theme.Colors
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ScheduleDialog(
    schedule: Schedule = Schedule(
        time = LocalTime.of(0, 0),
        isEnabled = true,
    ),
    onDismiss: () -> Unit = {},
    onConfirm: (LocalTime, Boolean) -> Unit = { _, _ -> }
) {
    val timePickerState = rememberTimePickerState(
        initialHour = schedule.time.hour,
        initialMinute = schedule.time.minute
    )
    var isEnabled by remember { mutableStateOf(schedule.isEnabled) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        containerColor = Colors.commonWhite,
        title = {
            Text(
                "일정 설정",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(
                    state = timePickerState,
                    layoutType = TimePickerLayoutType.Vertical,
                    colors = TimePickerDefaults.colors(
                        containerColor = Colors.commonWhite,
                        clockDialColor = Colors.primarySub,
                        periodSelectorSelectedContainerColor = Colors.primaryMain,
                        periodSelectorSelectedContentColor = Colors.commonWhite,
                        timeSelectorSelectedContainerColor = Colors.primaryMain,
                        timeSelectorUnselectedContainerColor = Colors.primarySub,
                        timeSelectorSelectedContentColor = Colors.commonWhite
                    )
                )


                HorizontalDivider(color = Colors.dividerColor, thickness = 1.dp)

                // 활성화 여부
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isEnabled,
                        onCheckedChange = { isEnabled = it }
                    )
                    Text(
                        "활성화",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val selectedTime = LocalTime.of(
                        timePickerState.hour,
                        timePickerState.minute
                    )
                    onConfirm(selectedTime, isEnabled)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.primaryMain
                )
            ) {
                Text("확인")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = { onDismiss() }) {
                Text("취소")
            }
        }
    )
}
