package com.udacity.shoestore.ui.store.common

import androidx.lifecycle.ViewModel
import com.udacity.shoestore.domain.Shoe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StoreSharedViewModel : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    data class State(
        val shoes: List<Shoe> = emptyList(),
        var shoeName: String? = null,
        var shoeCompany: String? = null,
        var shoeSize: String? = null,
        var shoeDescription: String? = null,
        val navigateUp: Boolean = false
    )

    fun sendEvent(event: StoreEvent): Unit = when (event) {
        is StoreEvent.OnSave -> trySaveShoe(event.state)
        StoreEvent.OnCancel -> navigateUp()
        StoreEvent.ResetState -> _state.value = State()
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

    private fun validateForm(state: State): Boolean =
        state.shoeName.isNullOrBlank() || state.shoeCompany.isNullOrBlank() || state.shoeSize.isNullOrBlank() || state.shoeDescription.isNullOrBlank()
}