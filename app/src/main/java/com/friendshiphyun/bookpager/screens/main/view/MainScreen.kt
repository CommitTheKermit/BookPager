package com.friendshiphyun.bookpager.screens.main.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.friendshiphyun.bookpager.screens.main.view.schedule.ScheduleDialog
import com.friendshiphyun.bookpager.screens.main.view.schedule.ScheduleList
import com.friendshiphyun.bookpager.ui.theme.Colors

/**
 * 메인 화면
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen() {
    var showScheduleDialog by remember { mutableStateOf(false) }

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