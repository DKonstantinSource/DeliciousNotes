package com.example.deliciousnotes

import android.os.Bundle
import android.widget.Toast
import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.deliciousnotes.databinding.ActivityMainBinding
import pub.devrel.easypermissions.EasyPermissions

class HostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val CAMERA_REQUEST = 100
    private val STORAGE_REQUEST = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        requestPermissions()
    }

    private fun requestPermissions() {
        val cameraPermission = Manifest.permission.CAMERA
        val storagePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

        val permissionsNeeded = mutableListOf<String>()

        if (!EasyPermissions.hasPermissions(this, cameraPermission)) {
            permissionsNeeded.add(cameraPermission)
        }
        if (!EasyPermissions.hasPermissions(this, storagePermission)) {
            permissionsNeeded.add(storagePermission)
        }

        if (permissionsNeeded.isNotEmpty()) {
            EasyPermissions.requestPermissions(
                this,
                "Это приложение требует доступ к камере и хранилищу.",
                CAMERA_REQUEST,
                *permissionsNeeded.toTypedArray()
            )
        }
    }
}