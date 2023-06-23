package com.udacity.shoestore.ui.store.master

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentStoreMasterBinding
import com.udacity.shoestore.ui.store.common.StoreSharedViewModel
import com.udacity.shoestore.ui.utils.collectFlow
import com.udacity.shoestore.ui.utils.log

class StoreMasterFragment : Fragment(R.layout.fragment_store_master), MenuProvider {

    private lateinit var binding: FragmentStoreMasterBinding
    private val viewModel: StoreSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStoreMasterBinding.bind(view)

        setUpMenuHost()

        viewLifecycleOwner.collectFlow(viewModel.state) {
            it.shoes.toString().log("StoreMasterFragment")
            binding.shoes = it.shoes
        }

    }

    private fun setUpMenuHost() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        viewLifecycleOwner.collectFlow(viewModel.state) {
            it.shoes.toString().log("StoreMasterFragment")
            binding.shoes = it.shoes
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_actions, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        findNavController().apply {
            popBackStack(R.id.storeMasterFragment, true)
            navigate(R.id.loginFragment)
        }
        return true
    }
}