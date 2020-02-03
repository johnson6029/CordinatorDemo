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

class ThirdScreenFragment : Fragment() {


    //
    // region properties
    //

    private lateinit var screen3Btn: Button
    private lateinit var userName: EditText

    // endregion


    //
    // region implementation
    //

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.screen3, container, false)
        screen3Btn = view.findViewById(R.id.screen3Btn)
        userName = view.findViewById(R.id.editUserName)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coordinator = MainCoordinator

        userName.setText(coordinator.userSocialGraphDTO.userName)

        screen3Btn.setOnClickListener {
            coordinator.userSocialGraphDTO.userName = userName.text.toString()
            coordinator.socialGraphSent = true
            coordinator.nextView()
            coordinator.navigate(this.requireActivity())
        }

    }

    // endregion
}