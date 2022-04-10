package dev.rodosteam.questtime.screen.common

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.rodosteam.questtime.R
import dev.rodosteam.questtime.databinding.ActivityMainBinding
import dev.rodosteam.questtime.quest.repo.meta.QuestMetaRepo
import dev.rodosteam.questtime.utils.InternalStorage

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_library,
                R.id.navigation_external,
                R.id.navigation_editor,
                R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_library,
                R.id.navigation_external,
                R.id.navigation_editor,
                R.id.navigation_settings -> navView.visibility = View.VISIBLE
                else -> {
                    navView.visibility = View.GONE
                }
            }
        }

        // Copy quest resources to internal storage
        val intStorage = InternalStorage(applicationContext.filesDir)
        if (!intStorage.exists(QuestMetaRepo.PATH_IN_INTERNAL_ST)) {
            intStorage.copyFromResources(
                resources,
                QuestMetaRepo.DEFAULT_RESOURCES,
                QuestMetaRepo.PATH_IN_INTERNAL_ST
            )
        }
    }
}

