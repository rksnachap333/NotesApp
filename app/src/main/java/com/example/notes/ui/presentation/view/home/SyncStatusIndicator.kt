package com.example.notes.ui.presentation.view.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.notes.domain.enums.SyncStatus

@Composable
fun SyncStatusIndicator(status: SyncStatus) {
    val (color, symbol) = when (status) {
        SyncStatus.SYNCED -> Color(0xFF4CAF50) to "✓"
        SyncStatus.PENDING -> Color(0xFFFFC107) to "⟳"
        SyncStatus.FAILED -> Color(0xFFF44336) to "!"
    }

    Text(
        text = symbol,
        color = color,
        fontWeight = FontWeight.Bold
    )
}
