package com.example.coordinator.Cordinator

import android.app.Activity

/**
 * Created by Johnson on 03,February,2020
 */

interface Coordinator {
    var childCoordinators: ArrayList<Coordinator>
    var navigationActivity: Activity
    fun start()
}