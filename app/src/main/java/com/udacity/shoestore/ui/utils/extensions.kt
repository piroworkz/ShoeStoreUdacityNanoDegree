package com.udacity.shoestore.ui.utils

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

fun String.log(name: String = javaClass.simpleName) {
    Log.d("<-- $name", this)
}

fun <T> LifecycleOwner.collectFlow(
    flow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(lifecycleState) {
            flow
                .catch { "${it.message}".log("LifecycleOwner.collectFlow") }
                .collect(body)
        }
    }
}