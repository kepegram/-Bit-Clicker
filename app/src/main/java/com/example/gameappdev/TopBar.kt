package com.example.gameappdev

import androidx.compose.material.*
import androidx.compose.runtime.Composable

// parameters needed for created the topbar in each screen

@Composable
fun TopBar(
    title: String = ""
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}