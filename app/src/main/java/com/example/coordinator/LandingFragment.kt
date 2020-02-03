package com.example.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.coordinator.Cordinator.MainCoordinator
import com.example.coordinator.Model.UserSocialGraphDTO

/**
 * Created by Johnson on 30,January,2020
 */

class LandingFragment : Fragment() {


    //
    // region properties
    //


    private lateinit var loginBtn: Button
    private lateinit var signUpBtn: Button
    private lateinit var loginWithoutPermissionBtn: Button
    private lateinit var signUpWithPermissionBtn: Button


    // endregion


    //
    // region implementation
    //


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.landingscreen, container, false)
        loginBtn = view.findViewById(R.id.login_btn)
        signUpBtn = view.findViewById(R.id.sign_up_button)
        loginWithoutPermissionBtn = view.findViewById(R.id.loginWithoutPermissionBtn)
        signUpWithPermissionBtn = view.findViewById(R.id.signUpWithPermissionBtn)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coordinator = MainCoordinator

        signUpBtn.setOnClickListener {
            coordinator.userSocialGraphDTO = UserSocialGraphDTO()
            coordinator.permissionGranted = false
            coordinator.nextView()
            coordinator.navigate(this.requireActivity())
        }

        loginBtn.setOnClickListener {
            coordinator.userSocialGraphDTO = UserSocialGraphDTO(
                firstName = "Joe",
                lastName = "Test",
                realName = "Joe Test",
                email = "joe.test@gmail.com",
                userName = "JoeTest1234",
                password = "testpassword"
            )

            coordinator.permissionGranted = true
            coordinator.socialGraphSent = true
            coordinator.nextView()
            coordinator.navigate(this.requireActivity())
        }

        loginWithoutPermissionBtn.setOnClickListener {
            coordinator.userSocialGraphDTO = UserSocialGraphDTO(
                firstName = "Joe",
                lastName = "Test",
                realName = "Joe Test",
                email = "joe.test@gmail.com",
                userName = "JoeTest1234",
                password = "testpassword"
            )

            coordinator.permissionGranted = false
            coordinator.socialGraphSent = true
            coordinator.nextView()
            coordinator.navigate(this.requireActivity())
        }

        signUpWithPermissionBtn.setOnClickListener {
            coordinator.userSocialGraphDTO = UserSocialGraphDTO()
            coordinator.permissionGranted = true
            coordinator.socialGraphSent = false
            coordinator.nextView()
            coordinator.navigate(this.requireActivity())
        }
    }

    // endregion

}