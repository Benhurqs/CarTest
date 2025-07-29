package com.benhurqs.test.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope

@Composable
fun LaunchOne(block: suspend CoroutineScope.() -> Unit) {
    var key by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        if(!key) {
            key = true
            block()
        }
    }
}