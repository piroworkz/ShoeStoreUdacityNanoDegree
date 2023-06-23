package com.udacity.shoestore.ui.store.common

sealed interface StoreEvent {
    data class OnSave(val state: StoreSharedViewModel.State) : StoreEvent
    object OnCancel : StoreEvent
}