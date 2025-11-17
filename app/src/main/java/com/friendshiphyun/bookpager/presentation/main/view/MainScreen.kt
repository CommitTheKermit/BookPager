package com.friendshiphyun.bookpager.presentation.main.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.friendshiphyun.bookpager.presentation.main.view.schedule.ScheduleDialog
import com.friendshiphyun.bookpager.presentation.main.view.schedule.ScheduleList
import com.friendshiphyun.bookpager.presentation.main.viewmodel.FrameViewModel
import com.friendshiphyun.bookpager.ui.theme.Colors

/**
 * 메인 화면
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen(
    frameViewModel: FrameViewModel = viewModel()

) {
    var showScheduleDialog by remember { mutableStateOf(false) }
    val uiState by frameViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MainAppBar(
                onRefresh = {
                    frameViewModel.checkStatus()
                },
                isEnabled = uiState.isConnected
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // WiFi 상태 카드
            WifiStatusCard(
                isConnected = true,
                wifiName = "SK_WiFiA5E0"
            )

            ManualPager()
            HorizontalDivider(color = Colors.dividerColor, thickness = 5.dp)

            ScheduleList(
                schedules = emptyList(),
                onDialog = { showScheduleDialog = true }
            )
            HorizontalDivider(color = Colors.dividerColor, thickness = 5.dp)
        }
    }

    if (showScheduleDialog) {
        ScheduleDialog(
            onDismiss = { showScheduleDialog = false }
        )
    }
}