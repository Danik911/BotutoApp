package com.example.borutoapp.presentation.screens.home

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.presentation.screens.splash.SplashViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val allHeroes = viewModel.allHeroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeAppTopBar(onSearchClicked = {})
        }
    ) {

    }
}


