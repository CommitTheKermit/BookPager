package com.friendshiphyun.bookpager.presentation.main.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.friendshiphyun.bookpager.domain.model.Schedule
import com.friendshiphyun.bookpager.presentation.main.view.manualControl.ManualControl
import com.friendshiphyun.bookpager.presentation.main.view.schedule.ScheduleDialog
import com.friendshiphyun.bookpager.presentation.main.view.schedule.ScheduleList
import com.friendshiphyun.bookpager.presentation.main.viewmodel.FrameViewModel
import com.friendshiphyun.bookpager.presentation.main.viewmodel.MotorControlViewModel
import com.friendshiphyun.bookpager.presentation.main.viewmodel.ScheduleViewModel
import com.friendshiphyun.bookpager.ui.theme.Colors

/**
 * 메인 화면
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen(
    frameViewModel: FrameViewModel = viewModel(),
    motorControlViewModel: MotorControlViewModel = viewModel(),
    scheduleViewModel: ScheduleViewModel = viewModel()
) {
    val context = LocalContext.current
    var showScheduleDialog by remember { mutableStateOf(false) }
    val uiState by frameViewModel.uiState.collectAsState()
    val motorState by motorControlViewModel.motorState.collectAsState()
    val schedules by scheduleViewModel.schedules.collectAsState()
    val message by scheduleViewModel.message.collectAsState()

    LaunchedEffect(message) {
        message?.let { (msg, isSuccess) ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            scheduleViewModel.clearMessage()
            if (isSuccess) {
                showScheduleDialog = false
            }
        }
    }

    Scaffold(
        topBar = {
            MainAppBar(
                onRefresh = {
                    frameViewModel.checkStatus()
                },
                isEnabled = uiState.isConnected
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // WiFi 상태 카드
            WifiStatusCard(
                isConnected = uiState.isConnected,
                wifiName = uiState.esp32Status?.wifi,
            )

            ManualPager(
                onButtonClick = { frameViewModel.turnPage() },
                isLoading = uiState.isLoading
            )
            HorizontalDivider(color = Colors.dividerColor, thickness = 5.dp)

            ScheduleList(
                schedules = schedules,
                onDialog = { showScheduleDialog = true },
                onToggle = { scheduleViewModel.updateSchedule(Schedule()) },
                onDelete = { scheduleViewModel.deleteSchedule(Schedule()) },
            )
            HorizontalDivider(color = Colors.dividerColor, thickness = 5.dp)
            ManualControl(
                onMotorControl = { motor, forward ->
                    if (forward) {
                        motorControlViewModel.rotateMotorForward(motor)
                    } else {
                        motorControlViewModel.rotateMotorBackward(motor)
                    }
                },
                isLoading = motorState.isLoading
            )
            HorizontalDivider(color = Colors.dividerColor, thickness = 5.dp)
        }
    }

    if (showScheduleDialog) {
        ScheduleDialog(
            onDismiss = {
                showScheduleDialog = false
                scheduleViewModel.clearMessage()
            },
            onConfirm = { time, isEnabled ->
                scheduleViewModel.addSchedule(time, isEnabled)
            }
        )
    }
}