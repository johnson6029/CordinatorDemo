package com.example.coordinator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coordinator.Cordinator.MainCoordinator

class MainActivity : AppCompatActivity() {

    val coordinator = MainCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment()
    }

    private fun loadFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .add(R.id.container, coordinator.currentFragment)
            .commit()
    }
}
