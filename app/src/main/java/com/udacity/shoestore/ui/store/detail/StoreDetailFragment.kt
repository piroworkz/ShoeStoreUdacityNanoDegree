package com.udacity.shoestore.ui.store.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentStoreDetailBinding
import com.udacity.shoestore.ui.store.common.StoreEvent
import com.udacity.shoestore.ui.store.common.StoreSharedViewModel
import com.udacity.shoestore.ui.utils.collectFlow

class StoreDetailFragment : Fragment(R.layout.fragment_store_detail) {

    private lateinit var binding: FragmentStoreDetailBinding
    private lateinit var state: StoreDetailState
    private val viewModel: StoreSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreDetailBinding.bind(view)

        binding.viewModel = this@StoreDetailFragment.viewModel
        state = buildState()

        viewLifecycleOwner.collectFlow(viewModel.state) { uiState: StoreSharedViewModel.State ->
            binding.state = uiState
            navigate(uiState.navigateUp)
        }
    }

    private fun navigate(navigateUp: Boolean) {
        if (navigateUp) {
            state.navigateUp(onDone = { viewModel.sendEvent(StoreEvent.OnCancel) })
        }
    }
}