package com.example.coordinator.Model

/**
 * Created by Johnson on 03,February,2020
 */

data class UserSocialGraphDTO(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var userName: String = "",
    var realName: String = ""
) {

    val socialGraphState: SocialGraphState
        get() {
            if (firstName.isEmpty() &&
                lastName.isEmpty() &&
                email.isEmpty()
                && userName.isEmpty()
                && password.isEmpty()
            ) {
                return SocialGraphState.empty
            } else if (!realName.isEmpty()
                && !email.isEmpty()
                && !userName.isEmpty()
            ) {
                return SocialGraphState.completeAuthProvider
            } else if (!realName.isEmpty()
                && !email.isEmpty()
                && !userName.isEmpty()
                && !password.isEmpty()
            ) {
                return SocialGraphState.completeESR
            }
            return SocialGraphState.incomplete
        }
}

enum class SocialGraphState {
    empty,
    completeAuthProvider,
    completeESR,
    incomplete,
}