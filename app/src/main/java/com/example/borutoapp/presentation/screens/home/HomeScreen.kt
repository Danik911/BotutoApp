package com.example.borutoapp.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.borutoapp.presentation.screens.splash.SplashViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val state by viewModel.onBoardingState.collectAsState()

    LaunchedEffect(key1 = true) {
        Log.d("HomeScreen", state.toString())
    }
}