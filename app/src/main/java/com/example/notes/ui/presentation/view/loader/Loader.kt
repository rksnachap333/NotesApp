package com.example.notes.ui.presentation.view.loader

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.ui.presentation.navigation.NavigationManager
import com.example.notes.ui.presentation.navigation.NavigationState
import kotlinx.coroutines.delay

@Composable
fun Loader(
    navigationManager: NavigationManager
) {
    LaunchedEffect(Unit) {
        delay(3_000)
        navigationManager.navigateTo(NavigationState.Home)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center,
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(R.drawable.sticky_notes),
                contentDescription = "Notes Icon",
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = "Notes are loading....",
            )
            Spacer(modifier = Modifier.height(8.dp))
            CircularProgressIndicator()
        }

    }

}