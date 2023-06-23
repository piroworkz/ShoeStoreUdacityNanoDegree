package com.udacity.shoestore.ui.store.common

sealed interface StoreEvent {
    data class OnNameChanged(val name: String) : StoreEvent
    data class OnBrandChanged(val brand: String) : StoreEvent
    data class OnSizeChanged(val size: String) : StoreEvent
    data class OnDescriptionChanged(val description: String) : StoreEvent
    object OnSave : StoreEvent
    object OnCancel : StoreEvent
}