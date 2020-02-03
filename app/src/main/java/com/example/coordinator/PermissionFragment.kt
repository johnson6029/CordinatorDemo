package com.example.coordinator

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.coordinator.Cordinator.MainCoordinator

/**
 * Created by Johnson on 30,January,2020
 */

class PermissionFragment : Fragment() {

    //
    // region properties

    //

    private lateinit var grantPermissionBtn: Button
    private lateinit var nextBtn: Button

    private fun granted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this.requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private val fineLocationGranted: Boolean
        get() = granted(Manifest.permission.ACCESS_FINE_LOCATION)

    private val coarseLocationGranted: Boolean
        get() = granted(Manifest.permission.ACCESS_COARSE_LOCATION)

    private val allLocationsGranted: Boolean
        get() = fineLocationGranted && coarseLocationGranted

    // endregion


    //
    // region implementation
    //

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.permissionlayout, container, false)
        grantPermissionBtn = view.findViewById(R.id.grantPermissionBtn)
        nextBtn = view.findViewById(R.id.nextBtn)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        grantPermissionBtn.setOnClickListener {
            ActivityCompat.requestPermissions(
                this.requireActivity(), arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), 110
            )
        }

        nextBtn.setOnClickListener {
            val coordinator = MainCoordinator

            coordinator.permissionGranted = allLocationsGranted

            if (coordinator.permissionGranted) {
                coordinator.nextView()
                activity?.finishAffinity()
                coordinator.navigate(this.requireActivity())
            } else {
                Toast.makeText(
                    this.requireContext(),
                    "Please grant permissions to next screen",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // endregion
}