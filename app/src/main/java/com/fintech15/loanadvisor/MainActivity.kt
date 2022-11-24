package com.fintech15.loanadvisor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.fintech15.loanadvisor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _: NavController, navDestination: NavDestination, _: Bundle? ->
            when(navDestination.id) {
                R.id.advice_fragment -> binding.bottomNavigation.isVisible = false
                R.id.login_fragment, R.id.registration_fragment -> {
                    binding.bottomNavigation.isVisible = false
                    binding.materialToolbar.isVisible = false
                }
                else -> {
                    binding.bottomNavigation.isVisible = true
                    binding.materialToolbar.isVisible = true
                }
            }
        }

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.materialToolbar.setupWithNavController(navController, appBarConfiguration)

        binding.bottomNavigation.setupWithNavController(navController)
        binding.navView.setupWithNavController(navController)
    }
}