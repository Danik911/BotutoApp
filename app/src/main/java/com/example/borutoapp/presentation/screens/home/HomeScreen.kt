package com.example.borutoapp.presentation.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.navigtion.Screen
import com.example.borutoapp.presentation.common.ListContent
import com.example.borutoapp.ui.theme.topAppBarBackgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val allHeroes = viewModel.allHeroes.collectAsLazyPagingItems()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.topAppBarBackgroundColor)

    Scaffold(
        topBar = {
            HomeAppTopBar(onSearchClicked = { navHostController.navigate(Screen.Search.route) })
        },
        content = {
            ListContent(heroes = allHeroes, navController = navHostController)
        }
    )
}


