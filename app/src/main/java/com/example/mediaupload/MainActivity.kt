package com.example.mediaupload

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mediaupload.presentation.auth.AuthViewModel
import com.example.mediaupload.presentation.events.EventViewModel
import com.example.mediaupload.presentation.navigation.AppNavGraph
import com.example.mediaupload.ui.theme.MediaUploadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaUploadTheme {
                val navController = rememberNavController()

                val authViewModel: AuthViewModel = viewModel()
                val eventViewModel: EventViewModel = viewModel()

                AppNavGraph(
                    navController = navController,
                    authViewModel = authViewModel,
                    eventViewModel = eventViewModel
                )
            }
        }
    }
}
