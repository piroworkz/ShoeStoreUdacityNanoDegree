package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigationUi()
        onDestinationChanged()
    }

    private fun setupNavigationUi() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.onFabClick = {
            navController.navigate(R.id.storeDetailFragment)
        }
    }

    private fun onDestinationChanged() {
        navController.addOnDestinationChangedListener { _, _: NavDestination, args ->
            val showNavIcon: Boolean = args?.getBoolean(getString(R.string.shownavicon)) == true
            if (!showNavIcon) binding.toolbar.navigationIcon = null
            binding.showAppBar = args?.getBoolean(getString(R.string.showappbar)) ?: true
            binding.showFab = args?.getBoolean(getString(R.string.showfab)) == true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
