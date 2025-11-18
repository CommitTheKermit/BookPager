package com.friendshiphyun.bookpager.presentation.main.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.friendshiphyun.bookpager.presentation.main.view.manualControl.ManualControl
import com.friendshiphyun.bookpager.presentation.main.view.schedule.ScheduleDialog
import com.friendshiphyun.bookpager.presentation.main.view.schedule.ScheduleList
import com.friendshiphyun.bookpager.presentation.main.viewmodel.FrameViewModel
import com.friendshiphyun.bookpager.presentation.main.viewmodel.MotorControlViewModel
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
) {
    var showScheduleDialog by remember { mutableStateOf(false) }
    val uiState by frameViewModel.uiState.collectAsState()
    val motorState by motorControlViewModel.motorState.collectAsState()

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
                schedules = emptyList(),
                onDialog = { showScheduleDialog = true }
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
        }
    }

    if (showScheduleDialog) {
        ScheduleDialog(
            onDismiss = { showScheduleDialog = false }
        )
    }
}