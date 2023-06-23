package com.udacity.shoestore.ui.instructions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment(R.layout.fragment_instructions) {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInstructionsBinding.bind(view).apply {
            clickListener = {
                findNavController().apply {
                    popBackStack(R.id.instructionsFragment, true)
                    navigate(R.id.storeMasterFragment)
                }
            }
        }
    }
}