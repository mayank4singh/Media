package com.example.mediaupload.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediaupload.presentation.auth.AuthViewModel
import com.example.mediaupload.presentation.auth.LoginScreen
import com.example.mediaupload.presentation.auth.SignupScreen
import com.example.mediaupload.presentation.events.EventListScreen
import com.example.mediaupload.presentation.events.CreateEventScreen
import com.example.mediaupload.presentation.events.EventViewModel
import com.example.mediaupload.presentation.home.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    eventViewModel: EventViewModel
) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN
    ) {

        // LOGIN SCREEN
        composable(NavRoutes.LOGIN) {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                },
                navigateToSignup = {
                    navController.navigate(NavRoutes.SIGNUP)
                }
            )
        }

        // SIGNUP SCREEN
        composable(NavRoutes.SIGNUP) {
            SignupScreen(
                viewModel = authViewModel,
                onSuccess = {
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(NavRoutes.SIGNUP) { inclusive = true }
                    }
                }
            )
        }

        // HOME SCREEN
        composable(NavRoutes.HOME) {
            HomeScreen(
                onEventsClick = {
                    navController.navigate(NavRoutes.EVENT_LIST)
                },
                onLogoutClick = {
                    authViewModel.logout()
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0)
                    }
                }
            )
        }

        // EVENT LIST
        composable(NavRoutes.EVENT_LIST) {
            EventListScreen(
                viewModel = eventViewModel,
                onCreateNewEvent = {
                    navController.navigate(NavRoutes.CREATE_EVENT)
                }
            )
        }

        // CREATE EVENT
        composable(NavRoutes.CREATE_EVENT) {
            CreateEventScreen(
                viewModel = eventViewModel,
                onEventCreated = {
                    navController.popBackStack()
                }
            )
        }
    }
}
