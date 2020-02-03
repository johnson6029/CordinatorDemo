package com.example.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.coordinator.Cordinator.MainCoordinator


/**
 * Created by Johnson on 30,January,2020
 */

class WelcomeScreenFragment : Fragment() {

    //
    // region properties
    //

    private lateinit var logoutBtn: Button

    // endregion


    //
    // region implementation
    //

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.welcome_screen, container, false)
        logoutBtn = view.findViewById(R.id.logoutBtn);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutBtn.setOnClickListener {
            val coordinator = MainCoordinator
            coordinator.deAuthorize()
            coordinator.navigate(this.requireActivity())
        }
    }

    // endregion
}