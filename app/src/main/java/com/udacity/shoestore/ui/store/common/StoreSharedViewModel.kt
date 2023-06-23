package com.udacity.shoestore.ui.store.common

import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StoreSharedViewModel : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    data class State(
        val shoes: List<Shoe> = emptyList(),
        val shoeName: String? = null,
        val shoeCompany: String? = null,
        val shoeSize: String? = null,
        val shoeDescription: String? = null,
        val navigateUp: Boolean = false
    )

    fun sendEvent(event: StoreEvent): Unit = when (event) {
        is StoreEvent.OnBrandChanged -> setBrand(event.brand)
        is StoreEvent.OnDescriptionChanged -> setDescription(event.description)
        is StoreEvent.OnNameChanged -> setName(event.name)
        is StoreEvent.OnSizeChanged -> setSize(event.size)
        StoreEvent.OnSave -> trySaveShoe(_state.value)
        StoreEvent.OnCancel -> navigateUp()
    }

    private fun setBrand(brand: String) {
        _state.update { s -> s.copy(shoeCompany = brand) }
    }

    private fun setDescription(description: String) {
        _state.update { s -> s.copy(shoeDescription = description) }
    }

    private fun setName(name: String) {
        _state.update { s -> s.copy(shoeName = name) }
    }

    private fun setSize(size: String) {
        _state.update { s -> s.copy(shoeSize = size) }
    }

    private fun navigateUp() {
        _state.update { s -> s.copy(navigateUp = !s.navigateUp) }
    }


    private fun trySaveShoe(state: State) {
        if (validateForm(state)) return
        onAddShoe(getShoe(state))
    }

    private fun onAddShoe(shoe: Shoe) {
        val updatedShoes = _state.value.shoes.toMutableList()
        updatedShoes.add(shoe)
        _state.update { s ->
            s.copy(
                shoes = updatedShoes,
                shoeName = null,
                shoeCompany = null,
                shoeSize = null,
                shoeDescription = null,
                navigateUp = !s.navigateUp
            )
        }
    }

    private fun getShoe(state: State): Shoe = Shoe(
        name = state.shoeName!!,
        company = state.shoeCompany!!,
        size = state.shoeSize?.toDouble()!!,
        description = state.shoeDescription!!,
        images = emptyList()
    )

    private fun validateForm(state: State): Boolean {
        if (state.shoeName.isNullOrBlank() || state.shoeCompany.isNullOrBlank() || state.shoeSize.isNullOrBlank() || state.shoeDescription.isNullOrBlank()) {
            return true
        }
        return false
    }
}