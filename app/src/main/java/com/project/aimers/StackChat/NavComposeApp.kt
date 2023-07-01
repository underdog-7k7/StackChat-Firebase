package com.project.aimers.StackChat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.project.aimers.StackChat.nav.Action
import com.project.aimers.StackChat.nav.Destination.AuthenticationOption
import com.project.aimers.StackChat.nav.Destination.Home
import com.project.aimers.StackChat.nav.Destination.Login
import com.project.aimers.StackChat.nav.Destination.Register
import com.project.aimers.StackChat.ui.theme.StackChatTheme
import com.project.aimers.StackChat.view.AuthenticationView
import com.project.aimers.StackChat.view.home.HomeView
import com.project.aimers.StackChat.view.login.LoginView
import com.project.aimers.StackChat.view.register.RegisterView

/**
 * The main Navigation composable which will handle all the navigation stack.
 */

@Composable
fun NavComposeApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    StackChatTheme {
        NavHost(
            navController = navController,
            startDestination =
            if (FirebaseAuth.getInstance().currentUser != null)
                Home
            else
                AuthenticationOption
        ) {
            composable(AuthenticationOption) {
                AuthenticationView(
                    register = actions.register,
                    login = actions.login
                )
            }
            composable(Register) {
                RegisterView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Login) {
                LoginView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Home) {
                HomeView()
            }
        }
    }
}