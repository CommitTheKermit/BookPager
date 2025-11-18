package com.friendshiphyun.bookpager.presentation.main.view.manualControl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SingleMotorItem(
    motorName: String,
    onForward: () -> Unit,
    onBackward: () -> Unit,
    isLoading: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = motorName,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = onForward,
            enabled = !isLoading,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A5A7A),
                contentColor = Color.White
            )
        ) {
            Text("정방향", style = MaterialTheme.typography.bodyMedium)
        }

        Button(
            onClick = onBackward,
            enabled = !isLoading,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5A4A5A),
                contentColor = Color.White
            )
        ) {
            Text("역방향", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
