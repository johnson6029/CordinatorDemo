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

class SecondScreenFragment : Fragment() {


    //
    // region properties
    //

    private lateinit var screen2Btn: Button
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText

    // endregion


    //
    // region implementation
    //

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.screen2, container, false)
        screen2Btn = view.findViewById(R.id.screen2Btn)
        firstName = view.findViewById(R.id.editFirstName)
        lastName = view.findViewById(R.id.editLastName)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coordinator = MainCoordinator

        firstName.setText(coordinator.userSocialGraphDTO.firstName)
        lastName.setText(coordinator.userSocialGraphDTO.lastName)

        screen2Btn.setOnClickListener {
            coordinator.userSocialGraphDTO.email = firstName.text.toString()
            coordinator.userSocialGraphDTO.password = lastName.text.toString()
            coordinator.nextView()
            coordinator.navigate(this.requireActivity())
        }
    }


    // endregion
}