package com.example.coordinator.Cordinator

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.coordinator.*
import com.example.coordinator.Model.UserSocialGraphDTO

/**
 * Created by Johnson on 03,February,2020
 */

object MainCoordinator : Coordinator {


    //
    // region properties
    //

    var userSocialGraphDTO: UserSocialGraphDTO = UserSocialGraphDTO()
    var socialGraphSent: Boolean = false
    var permissionGranted: Boolean = false
    var currentFragment: Fragment = LandingFragment()

    private val authState: AuthState
        get() {
            if (socialGraphSent && permissionGranted) {
                return AuthState.authenticatedWithPermission
            }

            if (socialGraphSent && !permissionGranted) {
                return AuthState.authenticatedNoPermission
            }

            if (!socialGraphSent && permissionGranted) {
                return AuthState.notAuthenticatedWithPermission
            }

            return AuthState.notAuthenticatedNoPermission
        }

    // endregion


    //
    // region implementation
    //


    override var childCoordinators: ArrayList<Coordinator>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override var navigationActivity: Activity
        get() = MainActivity()
        set(value) {
            value
        }

    override fun start() {
        currentFragment = LandingFragment()
        navigationActivity = MainActivity()
    }

    // endregion


    //
    // region helpers
    //

    fun nextView() {
        if (currentFragment is LandingFragment || currentFragment is PermissionFragment) {
            when (authState) {
                AuthState.notAuthenticatedNoPermission, AuthState.notAuthenticatedWithPermission -> {
                    currentFragment = getNextSignUpView()
                }

                AuthState.authenticatedWithPermission -> {
                    currentFragment = WelcomeScreenFragment()
                }

                AuthState.authenticatedNoPermission -> {
                    currentFragment = PermissionFragment()
                }
            }
        } else {
            currentFragment = getNextSignUpView()
        }
    }

    fun deAuthorize() {
        currentFragment = LandingFragment()
        userSocialGraphDTO = UserSocialGraphDTO()
        socialGraphSent = false
        permissionGranted = false
    }

    private fun getNextSignUpView(): Fragment {

        if (currentFragment is LandingFragment) {
            return FirstScreenFragment()
        } else if (currentFragment is FirstScreenFragment) {
            return SecondScreenFragment()
        } else if (currentFragment is SecondScreenFragment) {
            return ThirdScreenFragment()
        } else if (currentFragment is ThirdScreenFragment) {
            return if (!permissionGranted) {
                PermissionFragment()
            } else {
                WelcomeScreenFragment()
            }
        }

        return LandingFragment()
    }

    fun navigate(activity: Activity){
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }

    // endregion

    //
    // region outer structure
    //

    enum class AuthState {
        authenticatedWithPermission,
        authenticatedNoPermission,
        notAuthenticatedWithPermission,
        notAuthenticatedNoPermission
    }

    // endregion
}