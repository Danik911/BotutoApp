package com.example.borutoapp.presentation.screens.search

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.presentation.common.ListContent
import com.example.borutoapp.ui.theme.topAppBarBackgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {


    val searchQuery by searchViewModel.searchQuery
    val searchedHeroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.topAppBarBackgroundColor)

    Scaffold(
        topBar = {
            TopSearchBar(
                text = searchQuery,
                onTextChange = { searchViewModel.updateSearchQuery(query = it) },
                onSearchClick = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClick = {
                    navHostController.popBackStack()
                }
            )
        },
        content = {
            ListContent(
                heroes = searchedHeroes,
                navController = navHostController
            )
        }
    )
}

