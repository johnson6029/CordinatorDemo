package com.example.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.coordinator.Cordinator.MainCoordinator


/**
 * Created by Johnson on 30,January,2020
 */

class FirstScreenFragment : Fragment() {


    //
    // region properties
    //

    private lateinit var screen1Btn: Button
    private lateinit var email: EditText
    private lateinit var password: EditText

    // endregion


    //
    // region implementation
    //


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.screen1, container, false)
        screen1Btn = view.findViewById(R.id.screen1Btn)
        email = view.findViewById(R.id.editEmail)
        password = view.findViewById(R.id.editPass)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coordinator = MainCoordinator

        email.setText(coordinator.userSocialGraphDTO.email)
        password.setText(coordinator.userSocialGraphDTO.password)

        screen1Btn.setOnClickListener {
            coordinator.userSocialGraphDTO.email = email.text.toString()
            coordinator.userSocialGraphDTO.password = password.text.toString()
            coordinator.nextView()
            coordinator.navigate(this.requireActivity())
        }
    }

    // endregion
}