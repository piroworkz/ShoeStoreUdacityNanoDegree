package com.udacity.shoestore.ui.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.ui.welcome.WelcomeFragmentDirections.Companion.actionWelcomeFragmentToInstructionsFragment

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view).apply {
            clickListener = {
                findNavController().navigate(actionWelcomeFragmentToInstructionsFragment())
            }
        }
    }
}