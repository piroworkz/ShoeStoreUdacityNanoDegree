package com.udacity.shoestore.ui.store.detail

import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class StoreDetailState(private val nacController: NavController) {

    fun navigateUp(onDone: () -> Unit) {
        nacController.popBackStack()
        onDone()
    }
}

fun StoreDetailFragment.buildState(): StoreDetailState =
    StoreDetailState(nacController = findNavController())