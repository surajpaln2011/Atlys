package com.example.atlys.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun <T> MutableStateFlow<T>.update(scope: CoroutineScope, function: (T) -> T) {
    scope.launch {
        emit(function(value))
    }
}